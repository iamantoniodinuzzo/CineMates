package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.relations.MediaDefaultListCrossRef
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
    private lateinit var userDao: UserDao
    private lateinit var listDao: ListDao
    private lateinit var defaultListDao: DefaultListDao
    private var fakeListId: Int = 1
    private lateinit var defaultList1: DefaultListEntity
    private lateinit var defaultList2: DefaultListEntity

    @Before
    override fun setup() {
        super.setup()
        mediaDao = testDatabase.mediaDao()
        userDao = testDatabase.userDao()
        listDao = testDatabase.listDao()
        defaultListDao = testDatabase.defaultListDao()
        defaultList1 = DefaultListEntity(
            listId = 4,
            defaultTitle = "seen",
            ownerId = defaultUserEntity.userId
        )
        defaultList2 = DefaultListEntity(
            listId = 5,
            defaultTitle = "to_see",
            ownerId = defaultUserEntity.userId
        )
        listDao.insert(
            ListEntity(
                listId = fakeListId,
                title = "iuvaret",
                description = null,
                updateDate = Date(System.currentTimeMillis()),
                creationDate = Date(System.currentTimeMillis()),
                isPrivate = false,
                ownerId = defaultUserEntity.userId
            )
        )

        defaultListDao.insert(defaultList1)
        defaultListDao.insert(defaultList2)
        userDao.insert(defaultUserEntity)
        mediaDao.insert(defaultMediaEntity)
    }

    @Test
    fun insertUserFavMediaCrossRef() {
        val crossRef = UserFavMediaCrossRef(
            userId = defaultUserEntity.userId,
            mediaId = defaultMediaEntity.mediaId,
            favDate = Date(System.currentTimeMillis())
        )

        val result = mediaDao.insertUserFavMediaCrossRef(crossRef)
        assert(result > 0)
    }

    @Test
    fun getMedia() {
        val result = mediaDao.getMedia(defaultMediaEntity.mediaId)
        assertEquals(defaultMediaEntity.mediaId, result?.mediaId)
    }

    @Test
    fun getNullWhenMediaNotExists() {

        val result = mediaDao.getMedia(2)
        assertNull(result)
    }

    @Test
    fun getUserFavMedia() {
        val crossRef = UserFavMediaCrossRef(
            mediaId = defaultMediaEntity.mediaId,
            userId = defaultUserEntity.userId,
            favDate = Date(System.currentTimeMillis())
        )
        mediaDao.insertUserFavMediaCrossRef(crossRef)

        val result = mediaDao.getUserFavMedia(defaultMediaEntity.mediaId, defaultUserEntity.userId)
        assertNotNull(result)
        assertEquals(defaultMediaEntity.mediaId, result?.mediaId)
        assertEquals(defaultUserEntity.userId, result?.userId)
    }

    @Test
    fun deleteMediaFromFavorites() {
        val crossRef = UserFavMediaCrossRef(
            mediaId = defaultMediaEntity.mediaId,
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
        val list1 = ListEntity(
            listId = 1,
            title = "facilisis",
            description = null,
            updateDate = Date(System.currentTimeMillis()),
            creationDate = Date(System.currentTimeMillis()),
            isPrivate = false,
            ownerId = defaultUserEntity.userId
        )
        val list2 = ListEntity(
            listId = 2,
            title = "facis",
            description = null,
            updateDate = Date(System.currentTimeMillis()),
            creationDate = Date(System.currentTimeMillis()),
            isPrivate = false,
            ownerId = defaultUserEntity.userId
        )


        val mediaListCrossRef1 = MediaListCrossRef(
            listId = list1.listId,
            mediaId = defaultMediaEntity.mediaId,
            insertionDate = Date(System.currentTimeMillis()),
            position = 0,
        )
        val mediaListCrossRef2 = MediaListCrossRef(
            listId = list2.listId,
            mediaId = defaultMediaEntity.mediaId,
            insertionDate = Date(System.currentTimeMillis()),
            position = 1,
        )
        listDao.insert(list1)
        listDao.insert(list2)

        mediaDao.insert(defaultMediaEntity)
        mediaDao.insertMediaListCrossRef(mediaListCrossRef1)
        mediaDao.insertMediaListCrossRef(mediaListCrossRef2)

        // When
        runBlockingTest {
            val mediaWithLists = mediaDao.getMediaWithLists(defaultMediaEntity.mediaId)

            // Then
            assertNotNull(mediaWithLists)
            assertEquals(defaultMediaEntity, mediaWithLists[0].mediaEntity)
            assertEquals(2, mediaWithLists[0].listsWithMedia.size)
        }
    }

    // Test per il metodo getMediaInList
    @Test
    fun testGetMediaInList() {
        // Given
        val crossRef = MediaListCrossRef(
            listId = fakeListId, mediaId = defaultMediaEntity.mediaId,
            insertionDate = Date(System.currentTimeMillis()),
            position = 9772,
        )
        mediaDao.insertMediaListCrossRef(crossRef)

        // When
        runBlockingTest {
            val retrievedCrossRef = mediaDao.getMediaInList(fakeListId, defaultMediaEntity.mediaId)

            // Then
            assertNotNull(retrievedCrossRef)
            assertEquals(fakeListId, retrievedCrossRef?.listId)
            assertEquals(defaultMediaEntity.mediaId, retrievedCrossRef?.mediaId)
        }
    }

    // Test per il metodo insertMediaListCrossRef
    @Test
    fun testInsertMediaListCrossRef() {
        // Given
        val crossRef = MediaListCrossRef(
            listId = fakeListId,
            mediaId = defaultMediaEntity.mediaId,
            insertionDate = Date(System.currentTimeMillis()),
            position = 4087,
        )

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
        val crossRefToDelete = MediaListCrossRef(
            listId = fakeListId, mediaId = defaultMediaEntity.mediaId,
            insertionDate = Date(System.currentTimeMillis()),
            position = 8061,
        )
        mediaDao.insertMediaListCrossRef(crossRefToDelete)

        // When
        runBlockingTest {
            val deletedRowCount = mediaDao.deleteMediaFromList(crossRefToDelete)

            // Then
            assertEquals(1, deletedRowCount)
        }
    }


    //TEST DEFAULT LISTS

    @Test
    fun getMediaWithDefaultList_successfully() {
        //GIVEN - tutto quello che ti serve per testare

        mediaDao.insertMediaDefaultListCrossRef(
            MediaDefaultListCrossRef(
                mediaId = defaultMediaEntity.mediaId,
                listId = defaultList1.listId,
                insertionDate = Date(System.currentTimeMillis()),
                position = 0
            )
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = mediaDao.getMediaWithDefaultLists(defaultMediaEntity.mediaId)


        //THEN - verifica se il test è andato come ti aspettavi
        assertEquals(result[0].mediaEntity, defaultMediaEntity)
        assert(result[0].listsWithMedia.contains(defaultList1))

    }

    @Test
    fun deleteMediaFromDefaultList_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        mediaDao.insertMediaDefaultListCrossRef(
            MediaDefaultListCrossRef(
                mediaId = defaultMediaEntity.mediaId,
                listId = defaultList1.listId,
                insertionDate = Date(System.currentTimeMillis()),
                position = 0
            )
        )
        val mediaDefaultListCrossRef =
            mediaDao.getMediaInDefaultList(defaultList1.listId, defaultMediaEntity.mediaId)


        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = mediaDefaultListCrossRef?.let { mediaDao.deleteMediaFromDefaultList(it) }
        val mediaDefaultListCrossRefAfterDeletion =
            mediaDao.getMediaInDefaultList(defaultList1.listId, defaultMediaEntity.mediaId)

        //THEN - verifica se il test è andato come ti aspettavi
        assertNotNull(mediaDefaultListCrossRef)
        assertEquals(1, result)
        assertNull(mediaDefaultListCrossRefAfterDeletion)


    }


}