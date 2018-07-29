package com.github.ininmm.assignmentdemo.source.datasource

import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.Flowable

/**
 * WeatherAndWeek 資料接口
 * Created by Michael Lien
 * on 2018/7/27.
 */
interface WeatherAndWeekDataSource {

    fun insertWeather(vararg weather: Weather): Flowable<Boolean>

    fun insertAllWeatherWeek(entities: List<WeatherWeek>): Flowable<Boolean>

    fun loadWeatherAndWeek(): Flowable<List<WeatherAndWeek>>

    fun deleteWeather(entity: Weather): Flowable<Boolean>

    fun deleteWeatherById(weatherId: Long): Flowable<Boolean>

    fun deleteAllWeather(): Flowable<Boolean>

    fun deleteWeatherWeek(entity: WeatherWeek): Flowable<Boolean>

    fun deleteWeatherWeekById(weekId: Long): Flowable<Boolean>

    fun insertWeeksForWeather(weather: Weather, weeks: List<WeatherWeek>): Flowable<Boolean>
}