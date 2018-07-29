package com.github.ininmm.assignmentdemo.source.remote

import com.github.ininmm.assignmentdemo.source.WeatherAndWeekDataSource
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import com.github.ininmm.network.service.WeatherService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * 代理模式處理 WeatherNetWorkSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class WeatherRemoteSource(private val weatherService: WeatherService) : WeatherAndWeekDataSource {

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

    override fun loadWeathers(): Flowable<List<WeatherAndWeek>> {
        return weatherService.getWeather().flatMap { weatherRss ->
            // 第二個 item 才是需要的
            val weatherItem = weatherRss.channel?.weatherItems?.get(1)
            val weather: Weather = Weather().apply {
                this.title = weatherItem?.title ?: this.title
            }
            val splitData = splitWeather((weatherItem?.description ?: "UnKnownError"))

            var weatherWeek: MutableList<WeatherWeek> = mutableListOf()

            for (i in 0..splitData.size) {
                weatherWeek.add(WeatherWeek(splitData[i]))
            }

            val weatherAndWeek = WeatherAndWeek(weather).apply {
                this.weatherWeeks = weatherWeek
            }

            return@flatMap Observable.just(listOf(weatherAndWeek))
        }.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun addWeathers(entity: WeatherAndWeek): List<Long> {
        throw UnsupportedOperationException("Unsupported")
    }

    override fun clearData(): Int {
        throw UnsupportedOperationException("Unsupported")
    }

    override fun deleteWeatherItem(entity: WeatherWeek): Int {
        throw UnsupportedOperationException("Unsupported")
    }

    private fun splitWeather(splitData: String): List<String> {
        return splitData.split("<BR>")
    }
}