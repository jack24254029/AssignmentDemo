package com.github.ininmm.common.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 提供 RxJava 封裝後的 Scheduler
 * Created by Michael Lien
 * on 2018/7/24.
 */
class SchedulerProvider : ISchedulerProvider, ThreadHandler by ThreadHandlerImpl() {
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun single(): Scheduler {
        return Schedulers.single()
    }

    override fun cacheExecutor(): Scheduler {
        return Schedulers.from(getCacheExecutor())
    }
}