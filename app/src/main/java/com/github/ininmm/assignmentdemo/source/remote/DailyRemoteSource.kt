package com.github.ininmm.assignmentdemo.source.remote

import com.github.ininmm.assignmentdemo.source.DailyWordDataSource
import com.github.ininmm.database.entity.DailyWord
import com.github.ininmm.network.ApiUtils
import io.reactivex.Flowable

/**
 * 代理模式處理 DailyNetWorkSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class DailyRemoteSource : DailyWordDataSource {

    override fun loadDailyWords(): Flowable<List<DailyWord>> {
        return ApiUtils.getJsoup().map { document ->
            val rawString = document.select(".dphs").text()
            return@map mutableListOf(DailyWord(rawString))
        }
    }

    override fun addDailyWord(entity: DailyWord): Long {
        throw UnsupportedOperationException("Unsupported")
    }

    override fun clearData(): Int {
        throw UnsupportedOperationException("Unsupported")
    }

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
}