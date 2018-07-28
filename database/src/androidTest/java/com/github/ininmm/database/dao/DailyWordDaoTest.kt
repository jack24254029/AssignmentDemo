package com.github.ininmm.database.dao

import android.support.test.runner.AndroidJUnit4
import com.github.ininmm.database.TestUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
@RunWith(AndroidJUnit4::class)
class DailyWordDaoTest : DbTest() {

    private val dailyWord = listOf(TestUtils.createDailyWord("foo"),
            TestUtils.createDailyWord("bar"))
    @Test
    fun insertAllAndLoad() {

        // 準備 -> 執行 -> 驗證


        val ids = db.dailyWordDao().insertAll(dailyWord)
        assertEquals(2, ids.size)

        val test = db.dailyWordDao().loadAll().test()
        test.apply {
            assertNoErrors()
            assertValue(dailyWord)
            System.out.println(values()[0][0].word)
        }
    }

    @Test
    fun insertAndDelete() {
        val id = db.dailyWordDao().insert(dailyWord[0])
        assertEquals(1, id)
        val value = db.dailyWordDao().deleteById(id)
        assertEquals(true, value > 0)
    }
}