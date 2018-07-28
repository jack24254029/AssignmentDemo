package com.github.ininmm.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 天氣總覽
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Entity
data class Weather (

        /**
         * 標題
         */
        var title: String
) {
    @PrimaryKey(autoGenerate = true)
    var weatherId: Long = 0
}