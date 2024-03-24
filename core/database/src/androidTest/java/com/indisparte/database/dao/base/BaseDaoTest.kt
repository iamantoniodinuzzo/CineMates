package com.indisparte.database.dao.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.base.MediaType
import com.indisparte.database.CineMatesDatabase
import com.indisparte.database.di.DatabaseModule
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
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
import java.util.Date
import java.util.concurrent.Executors

/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
open class BaseDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var testDatabase: CineMatesDatabase
    protected lateinit var defaultUserEntity:  UserEntity
    protected lateinit var defaultActorEntity: ActorEntity
    protected lateinit var defaultMediaEntity: MediaEntity

    private lateinit var  testDispatcher:TestDispatcher


    @Before
    open fun setup() {
        testDispatcher = StandardTestDispatcher()
        defaultUserEntity = UserEntity(
            userId = 1,
            name = "Bernadette McCormick",
            subscriptionDate = Date(System.currentTimeMillis())
        )
        defaultActorEntity = ActorEntity(actorId = 1, name = "Bryan Berger", posterPath = null)
        defaultMediaEntity = MediaEntity(
            mediaId = 1,
            mediaName = "Elwood Kelly",
            popularity = null,
            posterPath = null,
            voteAverage = 2.3,
            mediaType = MediaType.MOVIE.id
        )

        testDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CineMatesDatabase::class.java
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //io thread
                Executors.newSingleThreadExecutor().execute {
                    DatabaseModule.getInstance(ApplicationProvider.getApplicationContext()).userDao().insert(
                        defaultUserEntity
                    )
                }
                Executors.newSingleThreadExecutor().execute {
                    DatabaseModule.getInstance(ApplicationProvider.getApplicationContext()).personDao().insert(
                        defaultActorEntity
                    )
                }
                Executors.newSingleThreadExecutor().execute {
                    DatabaseModule.getInstance(ApplicationProvider.getApplicationContext()).mediaDao().insert(
                        defaultMediaEntity
                    )
                }
            }
        }).allowMainThreadQueries().build()
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