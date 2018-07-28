package com.github.ininmm.database.datasource

import io.reactivex.Flowable

/**
 * Base 層資料接口，把通用方法抽出來
 * Created by Michael Lien
 * on 2018/7/27.
 */
interface BaseDataSource<in T> {

    @JvmSuppressWildcards
    fun insert(vararg entity: T): Flowable<Boolean>

    @JvmSuppressWildcards
    fun insertAll(entities: List<T>): Flowable<Boolean>

    fun update(entity: T): Flowable<Boolean>

    fun delete(entity: T): Flowable<Boolean>
}