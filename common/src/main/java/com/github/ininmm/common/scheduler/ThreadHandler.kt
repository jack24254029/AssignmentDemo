package com.github.ininmm.common.scheduler

import java.util.concurrent.Executor

/**
 * Created by Michael Lien
 * on 2018/7/24.
 */
interface ThreadHandler {

    fun getCacheExecutor(): Executor
}