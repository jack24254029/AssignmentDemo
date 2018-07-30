package com.github.ininmm.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.ininmm.database.dao.DailyWordDao
import com.github.ininmm.database.dao.WeatherAndWeekDao
import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherWeek

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Database(entities = [Weather::class, WeatherWeek::class, DailyWord::class], version = 1)
abstract class AssignmentDataBase : RoomDatabase() {

    abstract fun weatherAndWeekDao(): WeatherAndWeekDao

    abstract fun dailyWordDao(): DailyWordDao

}