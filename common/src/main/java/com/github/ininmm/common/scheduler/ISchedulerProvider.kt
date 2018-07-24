package com.github.ininmm.common.scheduler

import io.reactivex.Scheduler

/**
 * Created by Michael Lien
 * on 2018/7/24.
 */
interface ISchedulerProvider {

    /**
     * 取得 UI Thread
     */

    fun ui(): Scheduler

    /**
     * 用於 CPU 密集的計算任務
     */
    fun computation(): Scheduler

    /**
     * 用於 I/O 密集型的操作
     */
    fun io(): Scheduler

    /**
     * 使用一個單例線程執行任務，任務會按照先進先出順序執行
     */
    fun single(): Scheduler

    /**
     * 取得一個 CacheExecutor 機制的 Scheduler
     */
    fun cacheExecutor(): Scheduler
}