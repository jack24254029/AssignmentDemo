package com.github.ininmm.assignmentdemo.source.local

import com.github.ininmm.assignmentdemo.source.WeatherAndWeekDataSource
import com.github.ininmm.database.dao.WeatherAndWeekDao
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * 代理模式處理 WeatherAndWeekDataSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class WeatherLocalSource(private val weatherAndWeekDao: WeatherAndWeekDao) : WeatherAndWeekDataSource {

    companion object {
        private var INSTANCE: WeatherLocalSource? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(weatherAndWeekDao: WeatherAndWeekDao): WeatherLocalSource {
            return INSTANCE ?: synchronized(WeatherLocalSource::class.java) {
                INSTANCE ?: WeatherLocalSource(weatherAndWeekDao).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun loadWeathers(forceRefresh: Boolean): Flowable<List<WeatherAndWeek>> {
        return weatherAndWeekDao.loadWeatherAndWeek()
    }

    override fun addWeathers(entity: WeatherAndWeek): List<Long> {
        val list = entity.weatherWeeks
        return weatherAndWeekDao.insertWeeksForWeather(entity.weather, list)
    }

    override fun clearData(): Int {
        return weatherAndWeekDao.deleteAllWeather()
    }

    override fun deleteWeatherItem(entity: WeatherWeek): Int {
        return weatherAndWeekDao.deleteWeatherWeek(entity)
    }
}