package com.github.ininmm.database.dao

import android.support.test.runner.AndroidJUnit4
import com.github.ininmm.database.TestUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
@RunWith(AndroidJUnit4::class)
class WeatherAndWeekDaoTest : DbTest() {

    @Test
    fun insertAndLoad() {
        val weather = TestUtils.createWeather("臺中市 一週天氣預報")
        val weatherWeeks = TestUtils.createWeatherWeeks(5,
                "07/29 晚上 溫度:28 ~ 32 多雲")

        val weatherId = db.weatherAndWeekDao().insertWeather(weather)
        weather.weatherId = weatherId

        val weekIds = db.weatherAndWeekDao().insertWeeksForWeather(weather, weatherWeeks)

        val test = db.weatherAndWeekDao().loadWeatherAndWeek().test()
//        test.apply {
//            assertNoErrors()
//            assertEquals(values()[0][0].weather, weather)
//            assertEquals(values()[0][0].weatherWeeks, weatherWeeks)
//        }
    }

    @Test
    fun delete() {

    }
}