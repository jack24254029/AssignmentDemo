package com.github.ininmm.assignmentdemo.repository

import com.github.ininmm.assignmentdemo.source.DailyWordDataSource
import com.github.ininmm.common.scheduler.ISchedulerProvider
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
class DailyRepository(private val dailyLocalSource: DailyWordDataSource,
                      private val dailyRemoteSource: DailyWordDataSource,
                      private val schedulerProvider: ISchedulerProvider) : DailyWordDataSource {

    companion object {
        private var INSTANCE: DailyRepository? = null

        /**
         * 單例，沒用 Dagger 前先這樣，或是直接 new instance
         */
        fun getInstance(dailyLocalSource: DailyWordDataSource,
                        dailyRemoteSource: DailyWordDataSource,
                        schedulerProvider: ISchedulerProvider): DailyRepository {
            return INSTANCE ?: synchronized(DailyRepository::class.java) {
                INSTANCE ?: DailyRepository(dailyLocalSource,
                        dailyRemoteSource,
                        schedulerProvider).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    var caches = mutableListOf<DailyWord>()

    override fun loadDailyWords(forceRefresh: Boolean): Flowable<List<DailyWord>> {
        if (forceRefresh) return refreshData()
        return if (caches.size > 0) {
            // 如果有暫存，直接返回
            Flowable.just(caches)
        } else {
            dailyLocalSource.loadDailyWords(false)
                    .take(1)
                    .flatMap { Flowable.fromIterable(it) }
                    .doOnNext { caches.add(it) }
                    .toList()
                    .toFlowable()
                    .filter { it.isNotEmpty() }
                    .switchIfEmpty(refreshData())
            // 如果本地是空的，直接從遠端獲取資料
        }
    }

    override fun addDailyWord(entity: DailyWord): Long {
        caches.clear()
        caches.add(entity)
        return dailyLocalSource.addDailyWord(entity)
    }

    override fun clearData(): Int {
        caches.clear()
        return dailyLocalSource.clearData()
    }

    /**
     * 呼叫 API 更新，並把資料存進本地及暫存
     */
    fun refreshData(): Flowable<List<DailyWord>> {
        return dailyRemoteSource.loadDailyWords(true)
                .doOnNext {
                    caches.clear()
                    dailyLocalSource.clearData()
                }.flatMap {
                    Flowable.fromIterable(it)
                }.doOnNext {
                    caches.add(it)
                    dailyLocalSource.addDailyWord(it)
                }.toList().toFlowable()
    }
}