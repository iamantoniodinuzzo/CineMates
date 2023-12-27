package com.indisparte.database.dao.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.CineMatesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
abstract class BaseDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var testDatabase: CineMatesDatabase
    private lateinit var  testDispatcher:TestDispatcher


    @Before
    open fun setup() {
        testDispatcher = StandardTestDispatcher()
        testDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CineMatesDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    open fun tearDown() {
        testDatabase.close()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    protected fun runBlockingTest(block: suspend () -> Unit) {
        runBlocking {
            Dispatchers.setMain(testDispatcher)
            block()
            Dispatchers.resetMain()
        }
    }
}