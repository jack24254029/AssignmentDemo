package com.github.ininmm.database.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.github.ininmm.database.AssignmentDataBase
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * Created by Michael Lien
 * on 2018/7/28.
 */
abstract class DbTest {
    @get:Rule
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var _db: AssignmentDataBase

    val db: AssignmentDataBase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AssignmentDataBase::class.java).allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        _db.close()
    }
}