package com.github.ininmm.assignmentdemo.source.datasource.local

import com.github.ininmm.assignmentdemo.source.datasource.DailyWordDataSource
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

    override fun deleteById(dailyId: Long): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAll(): Flowable<List<DailyWord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(vararg entity: DailyWord): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertAll(entities: List<DailyWord>): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(entity: DailyWord): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(entity: DailyWord): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}