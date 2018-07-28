package com.github.ininmm.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Flowable

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Dao
interface DailyWordDao : BaseDao<DailyWord> {

    @Query("DELETE FROM DailyWord WHERE dailyId = :dailyId")
    fun deleteById(dailyId: Long): Int

    @Query("SELECT * FROM DailyWord")
    fun loadAll(): Flowable<List<DailyWord>>
}