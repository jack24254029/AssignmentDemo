package com.github.ininmm.assignmentdemo.source.datasource

import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
interface DailyWordDataSource : BaseDataSource<DailyWord> {

    fun deleteById(dailyId: Long): Flowable<Boolean>

    fun loadAll(): Flowable<List<DailyWord>>
}