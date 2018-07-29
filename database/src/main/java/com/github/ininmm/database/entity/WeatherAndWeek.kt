package com.github.ininmm.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * 關聯式 Data Class，把 [Weather] 跟 [WeatherWeek] 關聯起來
 * Created by Michael Lien
 * on 2018/7/27.
 */
data class WeatherAndWeek (

        @Embedded
        var weather: Weather


) {
        @Relation(parentColumn = "weatherId", entityColumn = "weatherId", entity = WeatherWeek::class)
        var weatherWeeks: MutableList<WeatherWeek> = mutableListOf()
}