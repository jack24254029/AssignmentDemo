package com.github.ininmm.assignmentdemo.contract

import com.github.ininmm.arch.base.BasePresenter
import com.github.ininmm.database.entity.WeatherWeek

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
interface MainPageContract {

    interface View {
        fun showDeleteItemMessage()
    }

    interface Presenter : BasePresenter {
        fun deleteWeatherWeek(weatherWeek: WeatherWeek)
    }
}