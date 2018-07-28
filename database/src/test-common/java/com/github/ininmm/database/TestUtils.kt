package com.github.ininmm.database

import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherWeek

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
object TestUtils {

    fun createDailyWord(word: String) = DailyWord(word = word)

    fun createWeather(title: String) = Weather(title = title)

    fun createWeatherWeek(date: String,
                          timePeriod: String,
                          temperature: String,
                          description: String): WeatherWeek {
        return WeatherWeek(date = date,
                timePeriod = timePeriod,
                temperature = temperature,
                description = description)
    }

    fun createWeatherWeeks(count: Int,
                           date: String,
                           timePeriod: String,
                           temperature: String,
                           description: String): List<WeatherWeek> {
        return (0 until count).map {
            createWeatherWeek(date = date + it,
                    timePeriod = timePeriod + it,
                    temperature = temperature + it,
                    description = description + it)
        }
    }
}