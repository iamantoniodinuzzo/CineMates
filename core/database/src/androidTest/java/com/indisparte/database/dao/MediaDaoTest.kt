package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.base.MediaType
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
import com.indisparte.database.entity.relations.GenreWithMedias
import com.indisparte.database.entity.relations.MediaListCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
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

    }

    @Test
    fun insertUserFavMediaCrossRef() {
        mediaDao.insert(fakeMedia)
        val crossRef = UserFavMediaCrossRef(
            userId = defaultUserEntity.userId,
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
    fun getNullWhenMediaNotExists() {

        val result = mediaDao.getMedia(2)
        assertNull(result)
    }

    @Test
    fun getUserFavMedia() {
        mediaDao.insert(fakeMedia)
        val crossRef = UserFavMediaCrossRef(
            mediaId = fakeMediaId,
            userId = defaultUserEntity.userId,
            favDate = Date(System.currentTimeMillis())
        )
        mediaDao.insertUserFavMediaCrossRef(crossRef)

        val result = mediaDao.getUserFavMedia(fakeMediaId, defaultUserEntity.userId)
        assertNotNull(result)
        assertEquals(fakeMediaId, result?.mediaId)
        assertEquals(defaultUserEntity.userId, result?.userId)
    }

    @Test
    fun deleteMediaFromFavorites() {
        mediaDao.insert(fakeMedia)

        val crossRef = UserFavMediaCrossRef(
            mediaId = fakeMediaId,
            userId = defaultUserEntity.userId,
            favDate = Date(System.currentTimeMillis())
        )
        mediaDao.insertUserFavMediaCrossRef(crossRef)

        val result = mediaDao.deleteUserFavMediaCrossRef(crossRef)
        assertEquals(1, result)
    }

    //MEDIA IN LIST
    @Test
    fun givenAMediaWithAssociatedListsWhenQueryingMediaWithListsThenTheCorrectMediaWithListsIsReturned() {
        // Given
        val media = MediaEntity(mediaId = 1, mediaName = "Test Media",
            popularity = null,
            posterPath = null,
            voteAverage = 2.3,
            mediaType = 8507, )
        val list1 = MediaListCrossRef(listId = 1, mediaId = 1, insertionDate = Date(System.currentTimeMillis()), position = 0,)
        val list2 = MediaListCrossRef(listId = 2, mediaId = 1, insertionDate =Date(System.currentTimeMillis()), position = 1,)

        mediaDao.insert(media)
        mediaDao.insertMediaListCrossRef(list1)
        mediaDao.insertMediaListCrossRef(list2)

        // When
        runBlockingTest {
            val mediaWithLists = mediaDao.getMediaWithLists(1)

            // Then
            assertNotNull(mediaWithLists)
            assertEquals(media, mediaWithLists[0].mediaEntity)
            assertEquals(2, mediaWithLists[0].listsWithMedia.size)
        }
    }

    // Test per il metodo getMediaInList
    @Test
    fun testGetMediaInList() {
        // Given
        val listId = 2
        val mediaId = 2
        val crossRef = MediaListCrossRef(listId = listId, mediaId = mediaId,
            insertionDate =Date(System.currentTimeMillis()),
            position = 9772,)
        mediaDao.insertMediaListCrossRef(crossRef)

        // When
        runBlockingTest {
            val retrievedCrossRef = mediaDao.getMediaInList(listId, mediaId)

            // Then
            assertNotNull(retrievedCrossRef)
            assertEquals(listId, retrievedCrossRef?.listId)
            assertEquals(mediaId, retrievedCrossRef?.mediaId)
        }
    }

    // Test per il metodo insertMediaListCrossRef
    @Test
    fun testInsertMediaListCrossRef() {
        // Given
        val crossRef = MediaListCrossRef(listId = 3, mediaId = fakeMediaId, insertionDate =Date(System.currentTimeMillis()), position = 4087,)

        // When
        runBlockingTest {
            val insertedId = mediaDao.insertMediaListCrossRef(crossRef)

            // Then
            assertNotEquals(-1, insertedId)
        }
    }

    // Test per il metodo deleteMediaFromList
    @Test
    fun testDeleteMediaFromList() {
        // Given
        val crossRefToDelete = MediaListCrossRef(listId = 4, mediaId = 4,
            insertionDate =Date(System.currentTimeMillis()),
            position = 8061,)
        mediaDao.insertMediaListCrossRef(crossRefToDelete)

        // When
        runBlockingTest {
            val deletedRowCount = mediaDao.deleteMediaFromList(crossRefToDelete)

            // Then
            assertEquals(1, deletedRowCount)
        }
    }



}