package com.github.ininmm.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Maybe

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Dao
interface DailyWordDao : BaseDao<DailyWord> {

    @Query("DELETE FROM DailyWord WHERE dailyId = :dailyId")
    fun deleteById(dailyId: Long): Int

    @Query("SELECT * FROM DailyWord")
    fun loadAll(): Maybe<List<DailyWord>>

    @Query("SELECT * FROM DailyWord WHERE dailyId = :id")
    fun load(id: Long): Maybe<DailyWord>
}