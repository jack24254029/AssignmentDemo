package com.github.ininmm.assignmentdemo.repository

import com.github.ininmm.assignmentdemo.source.WeatherAndWeekDataSource
import com.github.ininmm.common.scheduler.ISchedulerProvider
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.Flowable

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class WeatherRepository(private val weatherLocalSource: WeatherAndWeekDataSource,
                        private val weatherRemoteSource: WeatherAndWeekDataSource,
                        private val schedulerProvider: ISchedulerProvider) : WeatherAndWeekDataSource {

    companion object {
        private var INSTANCE: WeatherRepository? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(weatherLocalSource: WeatherAndWeekDataSource,
                        weatherRemoteSource: WeatherAndWeekDataSource,
                        schedulerProvider: ISchedulerProvider): WeatherRepository {
            return INSTANCE ?: synchronized(DailyRepository::class.java) {
                INSTANCE ?: WeatherRepository(weatherLocalSource,
                        weatherRemoteSource,
                        schedulerProvider).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    var caches = mutableListOf<WeatherAndWeek>()

    override fun loadWeathers(): Flowable<List<WeatherAndWeek>> {
        return if (caches.size > 0) {
            Flowable.just(caches)
        } else {
            weatherLocalSource.loadWeathers()
                    .take(1)
                    .flatMap { Flowable.fromIterable(it) }
                    .doOnNext { caches.add(it) }
                    .toList()
                    .toFlowable()
                    .filter { it[0].weatherWeeks.isNotEmpty() }
                    .switchIfEmpty(refreshData())
        }
    }

    override fun addWeathers(entity: WeatherAndWeek): List<Long> {
        caches.clear()
        caches.add(entity)
        return weatherLocalSource.addWeathers(entity)
    }

    override fun clearData(): Int {
        caches.clear()
        return weatherLocalSource.clearData()
    }

    override fun deleteWeatherItem(entity: WeatherWeek): Int {
        caches[0].weatherWeeks.toMutableList().remove(entity)
        return weatherLocalSource.deleteWeatherItem(entity)
    }

    /**
     * 呼叫 API 更新，並把資料存進本地及暫存
     */
    fun refreshData(): Flowable<List<WeatherAndWeek>> {
        return weatherRemoteSource.loadWeathers()
                .doOnNext {
                    caches.clear()
                    weatherLocalSource.clearData()
                }.flatMap {
                    Flowable.fromIterable(it)
                }.doOnNext {
                    caches.add(it)
                    weatherLocalSource.addWeathers(it)
                }.toList().toFlowable()
    }
}