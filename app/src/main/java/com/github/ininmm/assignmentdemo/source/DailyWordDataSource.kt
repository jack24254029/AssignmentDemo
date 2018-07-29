package com.github.ininmm.assignmentdemo.source

import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
interface DailyWordDataSource {

    fun loadDailyWords(forceRefresh: Boolean): Flowable<List<DailyWord>>

    fun addDailyWord(entity: DailyWord): Long

    fun clearData(): Int
}