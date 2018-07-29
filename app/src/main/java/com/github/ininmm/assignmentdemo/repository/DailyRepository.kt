package com.github.ininmm.assignmentdemo.repository

import com.github.ininmm.assignmentdemo.source.datasource.DailyWordDataSource
import com.github.ininmm.assignmentdemo.source.networksource.DailyNetWorkSource
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class DailyRepository(private val dailyLocalSource: DailyWordDataSource,
                      private val dailyNetWorkSource: DailyNetWorkSource) : DailyWordDataSource, DailyNetWorkSource {

    companion object {
        private var INSTANCE: DailyRepository? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(dailyLocalSource: DailyWordDataSource,
                        dailyNetWorkSource: DailyNetWorkSource): DailyRepository {
            return INSTANCE ?: synchronized(DailyRepository::class.java) {
                INSTANCE ?: DailyRepository(dailyLocalSource, dailyNetWorkSource).also { INSTANCE = it }
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

    override fun getDailyWork(): Maybe<DailyWord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}