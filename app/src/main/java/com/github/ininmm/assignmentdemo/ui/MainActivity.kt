package com.github.ininmm.assignmentdemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.ininmm.assignmentdemo.R
import com.github.ininmm.common.scheduler.SchedulerProvider
import com.github.ininmm.common.util.logE
import com.github.ininmm.network.ApiUtils

class MainActivity : AppCompatActivity() {

    private val schedulerProvider = SchedulerProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testApi()

    }

    private fun testApi() {
        ApiUtils.testAPI.getWeather().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ weather ->
                    weather.channel?.weatherItems?.get(1)?.description?.let { rawString ->
                        val rawArray = rawString.split("<BR>")
                        rawArray.forEach { itemString -> logE(itemString) }
                    }
                }, Throwable::printStackTrace)
    }
}