package com.github.ininmm.assignmentdemo.source

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

    fun loadWeathers(forceRefresh: Boolean): Flowable<List<WeatherAndWeek>>

    fun addWeathers(entity: WeatherAndWeek): List<Long>

    fun clearData(): Int

    fun deleteWeatherItem(entity: WeatherWeek): Int
}