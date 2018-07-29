package com.github.ininmm.assignmentdemo.source.networksource

import com.github.ininmm.database.entity.DailyWord
import io.reactivex.Maybe

/**
 * Created by Michael Lien
 * on 2018/7/29.
 */
interface DailyNetWorkSource {

    fun getDailyWork(): Maybe<DailyWord>
}