package com.indisparte.database.dao

import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.relations.MediaWithLists
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class ListDaoTest: BaseDaoTest() {

    private lateinit var listDao: ListDao

    override fun setup() {
        super.setup()
        listDao = testDatabase.listDao()
    }

    @Test
    fun testGetList() {
        // Given
        val listId = 1
        val list = ListEntity(
            listId = listId,
            title = "periculis",
            description = null,
            updateDate = Date(System.currentTimeMillis()),
            creationDate =Date(System.currentTimeMillis()),
            isPrivate = false,
            ownerId = defaultUserEntity.userId
        )

        // When
        listDao.insert(list)
        val retrievedList = listDao.getList(listId)

        // Then
        assert(retrievedList != null)
        assert(retrievedList == list)
    }


}