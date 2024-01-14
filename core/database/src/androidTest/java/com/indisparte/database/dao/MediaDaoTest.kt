package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class MediaDaoTest : BaseDaoTest() {

    private lateinit var mediaDao: MediaDao
    private val fakeUserId = 1
    private val fakeMediaId = 1
    private lateinit var fakeMedia: MediaEntity

    @Before
    override fun setup() {
        super.setup()
        fakeMedia = MediaEntity(
            mediaId = fakeMediaId,
            mediaName = "Christa Cooke",
            popularity = null,
            posterPath = null,
            voteAverage = 14.15,
            mediaType = 1

        )
        mediaDao = testDatabase.mediaDao()
        testDatabase.userDao().insert(
            UserEntity(
                userId = fakeUserId,
                name = "Federico Freeman",
                subscriptionDate = Date(System.currentTimeMillis())
            )
        )
    }

    @Test
    fun insertUserFavMediaCrossRef() {
        mediaDao.insert(fakeMedia)
        val crossRef = UserFavMediaCrossRef(
            userId = fakeUserId,
            mediaId = fakeMediaId,
            favDate = Date(System.currentTimeMillis())
        )

        val result = mediaDao.insertUserFavMediaCrossRef(crossRef)
        assert(result > 0)
    }

    @Test
    fun getMedia() {
        mediaDao.insert(fakeMedia)

        val result = mediaDao.getMedia(fakeMediaId)
        assertEquals(fakeMediaId, result?.mediaId)
    }

    @Test
    fun getUserFavMedia() {
        mediaDao.insert(fakeMedia)
        val crossRef = UserFavMediaCrossRef(
            mediaId = fakeMediaId,
            userId = fakeUserId,
            favDate = Date(System.currentTimeMillis())
        )
        mediaDao.insertUserFavMediaCrossRef(crossRef)

        val result = mediaDao.getUserFavMedia(fakeMediaId, fakeUserId)
        assertNotNull(result)
        assertEquals(fakeMediaId, result?.mediaId)
        assertEquals(fakeUserId, result?.userId)
    }

    @Test
    fun deleteMediaFromFavorites() {
        mediaDao.insert(fakeMedia)

        val crossRef = UserFavMediaCrossRef(
            mediaId = fakeMediaId,
            userId = fakeUserId,
            favDate = Date(System.currentTimeMillis())
        )
        mediaDao.insertUserFavMediaCrossRef(crossRef)

        val result = mediaDao.deleteUserFavMediaCrossRef(crossRef)
        assertEquals(1, result)
    }


}