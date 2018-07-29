package com.github.ininmm.assignmentdemo.presenter

import com.github.ininmm.assignmentdemo.contract.MainPageContract
import com.github.ininmm.assignmentdemo.repository.DailyRepository
import com.github.ininmm.assignmentdemo.repository.WeatherRepository
import com.github.ininmm.common.scheduler.ISchedulerProvider
import com.github.ininmm.database.entity.WeatherWeek

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class MainPagePresenter(private val dailyRepository: DailyRepository,
                        private val weatherRepository: WeatherRepository,
                        private val mainView: MainPageContract.View,
                        private val schedulerProvider: ISchedulerProvider) : MainPageContract.Presenter {
    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWeatherWeek(weatherWeek: WeatherWeek) {
        mainView.showDeleteItemMessage()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}