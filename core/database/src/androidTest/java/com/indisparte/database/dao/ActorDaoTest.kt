package com.indisparte.database.dao


import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.UserEntity
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

// FIXME: Ogni volta devo inserire l'utente e l'attore. Questo deve essere generalizzato specialmente per l'utente 
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class ActorDaoTest : BaseDaoTest() {

    private lateinit var actorDao: ActorDao
    private lateinit var userDao: UserDao
    private lateinit var userEntity: UserEntity
    private lateinit var actorEntity: ActorEntity
    private lateinit var userFavActorCrossRef: UserFavActorCrossRef

    @Before
    override fun setup() {
        super.setup()
        actorDao = testDatabase.personDao()
        userDao = testDatabase.userDao()
        actorEntity = ActorEntity(actorId = 1, name = "Bryan Berger", posterPath = null)
        userEntity = UserEntity(userId = 1, name = "Jordan Cochran", subscriptionDate = Date(System.currentTimeMillis()))
        userFavActorCrossRef = UserFavActorCrossRef(
            actorEntity.actorId,
            1,
            favDate = Date(System.currentTimeMillis())
        ) // Assuming actorId and userId are both 1



    }

    @Test
    fun insertActor() = runBlockingTest {
        val actorInsertionResult = actorDao.insert(actorEntity)
        assertEquals(1, actorInsertionResult)

    }

    @Test
    fun getActor() = runBlockingTest {
         actorDao.insert(actorEntity)
        val result = actorDao.getActor(actorEntity.actorId)
        assertEquals(actorEntity, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertUserFavActor_insertsAndRetrievesCorrectly() = runBlockingTest {
        actorDao.insert(actorEntity)
        userDao.insert(userEntity)
        val rowId = actorDao.insertUserFavActor(userFavActorCrossRef)
        assertEquals(1, rowId)

        val retrievedActor = actorDao.getActor(userFavActorCrossRef.actorId)
        assertEquals(userFavActorCrossRef.actorId, retrievedActor?.actorId)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteUserFavActor_deletesCorrectly() = runBlockingTest {
        actorDao.insert(actorEntity)
        userDao.insert(userEntity)
        actorDao.insertUserFavActor(userFavActorCrossRef)

        val deletedRows =
            actorDao.deleteUserFavActor(userFavActorCrossRef.actorId, userFavActorCrossRef.userId)
        assertEquals(1, deletedRows)

        val deleteActor = actorDao.delete(actorEntity)

        val retrievedActor = actorDao.getActor(userFavActorCrossRef.actorId)
        assertNull(retrievedActor)
    }
}
