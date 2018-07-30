package com.github.ininmm.assignmentdemo.contract

import com.github.ininmm.arch.base.BasePresenter
import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.database.entity.Weather
import com.github.ininmm.database.entity.WeatherWeek

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
interface MainPageContract {

    interface View {
        fun showDeleteItemMessage()

        fun clearTitle()

        fun clearList()

        fun clearDailyWord()

        fun showDailyWord(dailyWord: DailyWord)

        fun showDailyTitle(weatherTitle: String)

        fun showWeatherList(weatherWeek: List<WeatherWeek>)

        fun showErrorMessage()

        fun showRefreshing()

        fun stopRefreshing()
    }

    interface Presenter : BasePresenter {
        fun loadData(forceRefresh: Boolean)

        fun refresh()

        fun deleteWeatherWeek(weatherWeek: WeatherWeek)
    }
}