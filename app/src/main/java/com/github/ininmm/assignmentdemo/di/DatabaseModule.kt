package com.github.ininmm.assignmentdemo.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.github.ininmm.database.AssignmentDataBase
import com.github.ininmm.database.dao.DailyWordDao
import com.github.ininmm.database.dao.WeatherAndWeekDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Michael Lien
 * on 2018/7/30.
 */
@Module
class DatabaseModule {
    companion object {
        private val DATABASE_NAME = "AssignmentDataBase.db"
        private const val DATABASE = "AssignmentDataBase"
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AssignmentDataBase {
        return Room.databaseBuilder(app, AssignmentDataBase::class.java, "AssignmentDataBase.db").build()
    }

    @Provides
    @Singleton
    fun provideDailyWordDao(dataBase: AssignmentDataBase): DailyWordDao {
        return dataBase.dailyWordDao()
    }

    @Provides
    @Singleton
    fun provideWeatherAndWeekDao(dataBase: AssignmentDataBase): WeatherAndWeekDao {
        return dataBase.weatherAndWeekDao()
    }
}