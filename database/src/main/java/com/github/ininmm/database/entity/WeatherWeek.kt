package com.github.ininmm.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * 一周內天氣
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Entity(foreignKeys = [
    (ForeignKey(entity = Weather::class,
            parentColumns = arrayOf("weatherId"),
            childColumns = arrayOf("weatherId"),
            onDelete = ForeignKey.CASCADE))])
data class WeatherWeek (

        /**
         * 天氣描述
         */
        var description: String = "1/1 半夜 溫度:-20 ~ -5 天打雷劈"
) {
    @PrimaryKey(autoGenerate = true)
    var weekId: Long = 0

    /**
     * 關聯的天氣總覽 ID
     */
    var weatherId: Long = 0
}