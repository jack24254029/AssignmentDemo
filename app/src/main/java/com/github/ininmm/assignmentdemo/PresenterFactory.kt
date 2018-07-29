package com.github.ininmm.assignmentdemo

import android.annotation.SuppressLint
import com.github.ininmm.assignmentdemo.contract.MainPageContract
import com.github.ininmm.assignmentdemo.presenter.MainPagePresenter
import com.github.ininmm.assignmentdemo.repository.DailyRepository
import com.github.ininmm.assignmentdemo.repository.WeatherRepository
import com.github.ininmm.common.scheduler.ISchedulerProvider

/**
 * 建造 Presenter 的工廠，外部所有 View 都要透過工廠獲得 Presenter
 * Created by Michael Lien
 * on 2018/7/29.
 */
class PresenterFactory private constructor(
        private val dailyRepository: DailyRepository,
        private val weatherRepository: WeatherRepository,
        private val schedulerProvider: ISchedulerProvider){

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: PresenterFactory? = null

        /**
         * 單例，沒用 Dagger 前先這樣
         */
        fun getInstance(): PresenterFactory {
            return INSTANCE ?: synchronized(PresenterFactory::class.java) {
                INSTANCE ?: PresenterFactory(
                        Injection.provideDailyRepository(),
                        Injection.provideWeatherRepository(),
                        Injection.provideSchedulerProvider()
                ).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    fun createMainPagePresenter(view: MainPageContract.View): MainPagePresenter {
        return MainPagePresenter(dailyRepository = dailyRepository,
                weatherRepository = weatherRepository,
                mainView = view,
                schedulerProvider = schedulerProvider)
    }
}