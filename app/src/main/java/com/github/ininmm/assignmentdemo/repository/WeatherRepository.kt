package com.github.ininmm.assignmentdemo.repository

import com.github.ininmm.assignmentdemo.source.datasource.WeatherAndWeekDataSource
import com.github.ininmm.assignmentdemo.source.networksource.WeatherNetWorkSource
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class WeatherRepository(private val weatherLocalSource: WeatherAndWeekDataSource,
                        private val weatherRemoteSource: WeatherNetWorkSource) : WeatherAndWeekDataSource, WeatherNetWorkSource {

    companion object {
        private var INSTANCE: WeatherRepository? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(weatherLocalSource: WeatherAndWeekDataSource,
                        weatherRemoteSource: WeatherNetWorkSource): WeatherRepository {
            return INSTANCE ?: synchronized(DailyRepository::class.java) {
                INSTANCE ?: WeatherRepository(weatherLocalSource, weatherRemoteSource).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun insertWeather(vararg weather: Weather): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertAllWeatherWeek(entities: List<WeatherWeek>): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadWeatherAndWeek(): Flowable<List<WeatherAndWeek>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWeather(entity: Weather): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWeatherById(weatherId: Long): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllWeather(): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWeatherWeek(entity: WeatherWeek): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWeatherWeekById(weekId: Long): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertWeeksForWeather(weather: Weather, weeks: List<WeatherWeek>): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherAndWeek(): Observable<WeatherAndWeek> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}