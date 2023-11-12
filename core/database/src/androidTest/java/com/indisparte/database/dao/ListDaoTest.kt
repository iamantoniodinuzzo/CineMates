package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.base.MediaType
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.ListItemEntity
import com.indisparte.database.entity.MediaEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class ListDaoTest : BaseDaoTest() {

    private lateinit var listDao: ListDao

    @Before
    override fun setup() {
        super.setup()
        listDao = testDatabase.getListDao()
    }

    @Test
    fun testInsertAndLoadListSuccess() = runBlockingTest {
        // GIVEN
        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )

        // WHEN
        val listId = listDao.insertList(list)

        // THEN
        val loadedList = listDao.getList(listId.toInt())
        assertNotNull(loadedList)
        assertEquals(list, loadedList)
    }

    @Test
    fun testDeleteListSuccess() = runBlockingTest {
        // GIVEN
        val list = ListEntity(
            title = "To Delete",
            description = "This list will be deleted"
        )
        val listId = listDao.insertList(list)

        // WHEN
        val result = listDao.deleteList(list)

        // THEN
        assertEquals(1, result)
        val deletedList = listDao.getList(listId.toInt())
        assertNull(deletedList)
    }

    @Test
    fun testGetMediaInListSuccess() = runBlockingTest {
        // GIVEN
        val media = MediaEntity(
            id = 1234,
            mediaName = "Test Media",
            popularity = 7.8,
            posterPath = "poster_path",
            voteAverage = 9.0,
            mediaType = MediaType.MOVIE.id
        )

        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )
        listDao.insertList(list)

        val listId = listDao.getAllLists().find { it.title == list.title }?.id!!

        listDao.insertMediaInList(listId, media, 0)

        // WHEN
        val loadedMedia = listDao.getMediaInList(listId)

        // THEN
        assertEquals(1, loadedMedia.size)
        assertEquals(media, loadedMedia[0])
    }

    @Test
    fun testGetMediaInListWithTypeSuccess() = runBlockingTest {
        // GIVEN
        val mediaType = MediaType.MOVIE.id

        val media1 = MediaEntity(
            id = 1001,
            mediaName = "Media 1",
            popularity = 7.5,
            posterPath = "poster_path_1",
            voteAverage = 8.0,
            mediaType = mediaType
        )
        val media2 = MediaEntity(
            id = 1002,
            mediaName = "Media 2",
            popularity = 7.0,
            posterPath = "poster_path_2",
            voteAverage = 8.5,
            mediaType = mediaType
        )
        val media3 = MediaEntity(
            id = 1003,
            mediaName = "Media 3",
            popularity = 7.2,
            posterPath = "poster_path_3",
            voteAverage = 8.2,
            mediaType = MediaType.TV.id
        )

        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )
        listDao.insertList(list)

        val listId = listDao.getAllLists().find { it.title == list.title }?.id!!

        val listItem1 = ListItemEntity(
            listId = listId,
            mediaId = media1.id,
            position = 1,
            updateDate = "2023-10-30"
        )
        val listItem2 = ListItemEntity(
            listId = listId,
            mediaId = media2.id,
            position = 2,
            updateDate = "2023-10-30"
        )
        val listItem3 = ListItemEntity(
            listId = listId,
            mediaId = media3.id,
            position = 3,
            updateDate = "2023-10-30"
        )

        listDao.insertMediaInList(listId, media1, 0)
        listDao.insertMediaInList(listId, media2, 1)
        listDao.insertMediaInList(listId, media3, 2)
        // WHEN
        val loadedMedia = listDao.getMediaInListWithType(listId, mediaType)

        // THEN
        assertEquals(2, loadedMedia.size)
        assertTrue(loadedMedia.contains(media1))
        assertTrue(loadedMedia.contains(media2))
    }

    @Test
    fun testInsertMediaInListSuccess() = runBlockingTest {
        // GIVEN
        val media = MediaEntity(
            id = 1000,
            mediaName = "New Media",
            popularity = 7.5,
            posterPath = "poster_path",
            voteAverage = 8.0,
            mediaType = MediaType.MOVIE.id
        )
        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )
        val listId = listDao.insertList(list)

        // WHEN
        listDao.insertMediaInList(listId.toInt(), media, 1)

        // THEN
        val loadedMedia = listDao.getMediaInList(listId.toInt())
        assertEquals(1, loadedMedia.size)
        assertEquals(media, loadedMedia[0])
    }

    @Test
    fun testDeleteMediaFromListSuccess() = runBlockingTest {
        // GIVEN
        val media = MediaEntity(
            id = 1234,
            mediaName = "Test Media",
            popularity = 7.8,
            posterPath = "poster_path",
            voteAverage = 9.0,
            mediaType = MediaType.MOVIE.id
        )

        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )
        listDao.insertList(list)
        val listId = listDao.getAllLists().find { it.title == list.title }?.id!!

        val listItem = ListItemEntity(
            listId = listId,
            mediaId = media.id,
            position = 1,
            updateDate = "2023-10-30"
        )
        listDao.insertMediaInList(listId, media, 0)

        // WHEN
        listDao.deleteMediaFromList(listId, media.id)

        // THEN
        val loadedMedia = listDao.getMediaInList(listId)
        assertEquals(0, loadedMedia.size)
        assertNull(listDao.getMediaById(media.id))
    }

    /*  @Test
      fun testUpdateMediaPositionSuccess() = runBlockingTest {
          // GIVEN
          val media = MediaEntity(
              id = 1234,
              mediaName = "Test Media",
              popularity = 7.8,
              posterPath = "poster_path",
              voteAverage = 9.0,
              mediaType = MediaType.MOVIE.id
          )

          val list = ListEntity(
              title = "My List",
              description = "My List Description"
          )
          listDao.insertList(list)
          val listId = listDao.getAllLists().find { it.title == list.title }?.id!!

          val listItem = ListItemEntity(
              listId = listId,
              mediaId = media.id,
              position = 1,
              updateDate = "2023-10-30"
          )
          listDao.insertMediaInList(listId, media, 0)

          // WHEN
          listItem.position = 2
          listDao.updateMediaPosition(listItem)

          // THEN
          val loadedMedia = listDao.getMediaInList(listId)
          assertEquals(1, loadedMedia.size)
          assertEquals(2, loadedMedia[0].position)
      }*/

    @Test
    fun testUpdateListTitleAndDescriptionSuccess() = runBlockingTest {
        // GIVEN
        val list = ListEntity(
            title = "My List",
            description = "My List Description"
        )
        val listId = listDao.insertList(list)

        val updatedList = ListEntity(
            id = listId.toInt(),
            title = "Updated List",
            description = "Updated List Description"
        )

        // WHEN
        listDao.updateListTitleAndDescription(updatedList)

        // THEN
        val loadedList = listDao.getList(listId.toInt())
        assertNotNull(loadedList)
        assertEquals(updatedList, loadedList)
    }

}
