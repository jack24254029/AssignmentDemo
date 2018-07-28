package com.github.ininmm.database.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

/**
 *  Base Dao，把通用方法抽出來
 * Created by Michael Lien
 * on 2018/7/27.
 */
interface BaseDao<in T> {
    /**
     * 注意 insert 的返回值 Long 指的是這個 entity 在這張 table 裡的 primaryKey
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    /**
     * 使用List將資料新增入DB，如果衝突就會取代他
     * @param entities 要加入的資料
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(entities: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: T): Int

    @Delete
    fun delete(entity: T): Int
}