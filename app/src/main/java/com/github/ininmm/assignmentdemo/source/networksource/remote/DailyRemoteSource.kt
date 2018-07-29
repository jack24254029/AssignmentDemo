package com.github.ininmm.assignmentdemo.source.networksource.remote

import com.github.ininmm.assignmentdemo.source.networksource.DailyNetWorkSource
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Maybe

/**
 * 代理模式處理 DailyNetWorkSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class DailyRemoteSource : DailyNetWorkSource {

    companion object {
        private var INSTANCE: DailyRemoteSource? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(): DailyRemoteSource {
            return INSTANCE ?: synchronized(DailyRemoteSource::class.java) {
                INSTANCE ?: DailyRemoteSource().also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getDailyWork(): Maybe<DailyWord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}