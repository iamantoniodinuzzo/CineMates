package com.indisparte.database.dao

import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.DefaultListEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class DefaultListDaoTest:BaseDaoTest() {

    private lateinit var defaultListDao: DefaultListDao

    @Before
    override fun setup() {
        super.setup()
        defaultListDao = testDatabase.defaultListDao()
    }

    @Test
    fun test_Inserimento_Recupero_Lista_Default() {
        // Given
        val defaultListId = 1
        val defaultList = DefaultListEntity(defaultListId, "seen", defaultUserEntity.userId )

        // When
        defaultListDao.insert(defaultList)
        val retrievedList = defaultListDao.getDefaultList(defaultListId)

        // Then
        assert(retrievedList != null)
        assert(retrievedList!!.listId == defaultListId)
    }

    @Test
    fun test_Recupero_Lista_Inesistente() {
        // Given
        val nonExistentListId = 100

        // When
        val retrievedList = defaultListDao.getDefaultList(nonExistentListId)

        // Then
        assert(retrievedList == null)
    }


}