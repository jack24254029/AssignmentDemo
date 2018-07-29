package com.github.ininmm.database.dao

import android.arch.persistence.room.*
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherAndWeek
import com.github.ininmm.database.entity.WeatherWeek
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Dao
abstract class WeatherAndWeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(weather: Weather): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllWeatherWeek(entities: List<WeatherWeek>): List<Long>

    @Transaction
    @Query("SELECT * FROM Weather")
    abstract fun loadWeatherAndWeek(): Flowable<List<WeatherAndWeek>>

    @Delete
    abstract fun deleteWeather(entity: Weather): Int

    @Query("DELETE FROM Weather WHERE weatherId = :weatherId")
    abstract fun deleteWeatherById(weatherId: Long): Int

    @Query("DELETE FROM WEATHER")
    abstract fun deleteAllWeather(): Int

    @Delete
    abstract fun deleteWeatherWeek(entity: WeatherWeek): Int

    @Query("DELETE FROM WeatherWeek WHERE weekId = :weekId")
    abstract fun deleteWeatherWeekById(weekId: Long): Int

    fun insertWeeksForWeather(weather: Weather, weeks: List<WeatherWeek>): List<Long> {
        val id = insertWeather(weather)
        weeks.forEach {
            it.weatherId = id
        }

        return insertAllWeatherWeek(weeks)
    }
}