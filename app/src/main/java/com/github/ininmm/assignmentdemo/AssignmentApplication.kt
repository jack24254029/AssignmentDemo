package com.github.ininmm.assignmentdemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by Michael Lien
 * on 2018/7/25.
 */
class AssignmentApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}