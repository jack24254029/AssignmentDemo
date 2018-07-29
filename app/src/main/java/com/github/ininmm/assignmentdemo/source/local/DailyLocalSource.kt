package com.github.ininmm.assignmentdemo.source.local

import com.github.ininmm.assignmentdemo.source.DailyWordDataSource
import com.github.ininmm.database.dao.DailyWordDao
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable

/**
 * 代理模式處理 DailyWordDataSource
 * Created by Michael Lien
 * on 2018/7/29.
 */
class DailyLocalSource(private val dailyWordDao: DailyWordDao) : DailyWordDataSource {

    companion object {
        private var INSTANCE: DailyLocalSource? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(dailyWordDao: DailyWordDao): DailyLocalSource {
            return INSTANCE ?: synchronized(DailyLocalSource::class.java) {
                INSTANCE ?: DailyLocalSource(dailyWordDao).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun loadDailyWords(forceRefresh: Boolean): Flowable<List<DailyWord>> {
        return dailyWordDao.loadAll()
    }

    override fun addDailyWord(entity: DailyWord): Long {
        return dailyWordDao.insert(entity)
    }

    override fun clearData(): Int {
        return dailyWordDao.deleteAll()
    }
}