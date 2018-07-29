package com.github.ininmm.assignmentdemo

import com.github.ininmm.assignmentdemo.repository.DailyRepository
import com.github.ininmm.assignmentdemo.repository.WeatherRepository
import com.github.ininmm.assignmentdemo.source.local.DailyLocalSource
import com.github.ininmm.assignmentdemo.source.local.WeatherLocalSource
import com.github.ininmm.assignmentdemo.source.remote.DailyRemoteSource
import com.github.ininmm.assignmentdemo.source.remote.WeatherRemoteSource
import com.github.ininmm.common.scheduler.SchedulerProvider
import com.github.ininmm.database.AssignmentDataBase
import com.github.ininmm.database.dao.DailyWordDao
import com.github.ininmm.database.dao.WeatherAndWeekDao
import com.github.ininmm.network.ApiUtils
import com.github.ininmm.network.service.WeatherService

/**
 * 嘗試建立一個注入器，負責注入所有 Presenter Instance 需要的 parameter
 * 實務上要替換成 Dagger
 * Created by Michael Lien
 * on 2018/7/29.
 */
object Injection {

    private val database = AssignmentDataBase.getInstance(AssignmentApplication.context)

    /**
     * 提供 DailyRepository
     */
    fun provideDailyRepository(): DailyRepository {

        return DailyRepository.getInstance(provideDailyLocalSource(),
                provideDailyRemoteSource(),
                provideSchedulerProvider())
    }

    /**
     * 提供 WeatherRepository
     */
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository.getInstance(provideWeatherLocalSource(),
                provideWeatherRemoteSource(),
                provideSchedulerProvider())
    }

    /**
     * 提供 DailyLocalSource
     */
    fun provideDailyLocalSource(): DailyLocalSource {
        return DailyLocalSource.getInstance(provideDailyWordDao())
    }

    /**
     * 提供 DailyRemoteSource
     */
    fun provideDailyRemoteSource(): DailyRemoteSource {
        return DailyRemoteSource.getInstance()
    }

    /**
     * 提供 WeatherLocalSource
     */
    fun provideWeatherLocalSource(): WeatherLocalSource {
        return WeatherLocalSource.getInstance(provideWeatherAndWeekDao())
    }

    /**
     * 提供 WeatherRemoteSource
     */
    fun provideWeatherRemoteSource(): WeatherRemoteSource {
        return WeatherRemoteSource.getInstance(provideWeatherService())
    }

    /**
     * 提供 DailyWordDao
     */
    fun provideDailyWordDao(): DailyWordDao = database.dailyWordDao()

    /**
     * 提供 WeatherAndWeekDao
     */
    fun provideWeatherAndWeekDao(): WeatherAndWeekDao = database.weatherAndWeekDao()

    /**
     * 提供 WeatherService
     */
    fun provideWeatherService(): WeatherService {
        return ApiUtils.createService(WeatherService::class.java)
    }

    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider
    }
}