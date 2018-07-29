package com.github.ininmm.assignmentdemo.source.networksource.remote

import com.github.ininmm.assignmentdemo.source.networksource.WeatherNetWorkSource
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.network.service.WeatherService
import io.reactivex.Observable

/**
 * 代理模式處理 WeatherNetWorkSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class WeatherRemoteSource(private val weatherService: WeatherService) : WeatherNetWorkSource {

    companion object {
        private var INSTANCE: WeatherRemoteSource? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(weatherService: WeatherService): WeatherRemoteSource {
            return INSTANCE ?: synchronized(WeatherRemoteSource::class.java) {
                INSTANCE ?: WeatherRemoteSource(weatherService).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getWeatherAndWeek(): Observable<WeatherAndWeek> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}