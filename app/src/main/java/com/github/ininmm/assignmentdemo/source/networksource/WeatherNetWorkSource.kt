package com.github.ininmm.assignmentdemo.source.networksource

import com.github.ininmm.database.entity.WeatherAndWeek
import io.reactivex.Observable

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
interface WeatherNetWorkSource {



    fun getWeatherAndWeek(): Observable<WeatherAndWeek>
}