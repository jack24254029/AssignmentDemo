package com.github.ininmm.assignmentdemo.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.github.ininmm.assignmentdemo.AssignmentApplication
import com.github.ininmm.assignmentdemo.R
import com.github.ininmm.assignmentdemo.contract.MainPageContract
import com.github.ininmm.assignmentdemo.repository.DailyRepository
import com.github.ininmm.assignmentdemo.repository.WeatherRepository
import com.github.ininmm.common.scheduler.ISchedulerProvider
import com.github.ininmm.common.util.logE
import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class MainPagePresenter(private val dailyRepository: DailyRepository,
                        private val weatherRepository: WeatherRepository,
                        private val mainView: MainPageContract.View,
                        private val schedulerProvider: ISchedulerProvider) : MainPageContract.Presenter, LifecycleObserver {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        if (mainView is LifecycleOwner) {
            (mainView as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onLifeCycleResume() {
        logE("ON_RESUME")
        loadData(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onLifeCyclePause() {
        logE("ON_PAUSE")
        compositeDisposable.clear()
    }

    override fun refresh() {
        // 此方法的功能被 loadData 取代

        clearAllData()

        val disposable = Flowable.zip(dailyRepository.refreshData(),
                weatherRepository.refreshData(),
                BiFunction<List<DailyWord>,
                        List<WeatherAndWeek>,
                        Pair<List<DailyWord>, List<WeatherAndWeek>>> { listOfDaily, listOfWeather ->
                    val pair = listOfDaily to listOfWeather
                    pair
                }).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(this::handleReturnedData) {
                    it.printStackTrace()
                    handleError()
                }
        compositeDisposable.add(disposable)
    }

    override fun loadData(forceRefresh: Boolean) {
        // 清除畫面資料
        clearAllData()
        mainView.showDailyTitle(AssignmentApplication.context.getString(R.string.app_loading_title))

        val disposable = Flowable.zip(dailyRepository.loadDailyWords(forceRefresh),
                weatherRepository.loadWeathers(forceRefresh),
                BiFunction<List<DailyWord>,
                        List<WeatherAndWeek>,
                        Pair<List<DailyWord>, List<WeatherAndWeek>>> { listOfDaily, listOfWeather ->
                    val pair = listOfDaily to listOfWeather
                    pair
                }).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    this.handleReturnedData(it)

                }, {
                    it.printStackTrace()
                    handleError()
                })
        compositeDisposable.add(disposable)
    }

    override fun deleteWeatherWeek(weatherWeek: WeatherWeek) {
        val disposable = Observable.create<Boolean> {
            it.onNext(weatherRepository.deleteWeatherItem(weatherWeek) > 0)
            it.onComplete()
        }.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    if (it) {
                        mainView.showWeatherList(weatherRepository.caches[0].weatherWeeks)
                        mainView.showDeleteItemMessage()
                    } else {
                        handleError()
                    }
                }, {
                    it.printStackTrace()
                    handleError()
                })
        compositeDisposable.add(disposable)
    }

    /**
     * Updates view
     */
    private fun handleReturnedData(pair: Pair<List<DailyWord>, List<WeatherAndWeek>>) {
        if (pair.first.isNotEmpty()) {
            mainView.showDailyWord(pair.first[0])
        }

        if (pair.second.isNotEmpty()) {
            mainView.showDailyTitle(pair.second[0].weather.title)
            mainView.showWeatherList(pair.second[0].weatherWeeks)
        }
        mainView.stopRefreshing()
    }

    private fun handleError() {
        mainView.showErrorMessage()
        mainView.stopRefreshing()
    }

    private fun clearAllData() {
        mainView.clearTitle()
        mainView.clearList()
        mainView.clearDailyWord()
    }
}