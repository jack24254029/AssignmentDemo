package com.github.ininmm.common.scheduler

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Michael Lien
 * on 2018/7/24.
 */
class ThreadHandlerImpl : ThreadHandler {
    override fun getCacheExecutor(): Executor {
        return Executors.newCachedThreadPool()
    }
}