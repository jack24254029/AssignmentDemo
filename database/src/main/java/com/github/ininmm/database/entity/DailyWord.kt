package com.github.ininmm.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Entity
data class DailyWord (

        /**
         * 每日一句全部文字(很難找到規則把句子斷開)
         */
        var word: String
) {
    @PrimaryKey(autoGenerate = true)
    var dailyId: Long = 0
}