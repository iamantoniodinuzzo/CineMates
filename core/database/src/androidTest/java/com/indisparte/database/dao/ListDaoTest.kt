package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.ListEntity
import junit.framework.TestCase.assertFalse
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

    private lateinit var dao: ListDao

    @Before
    override fun setup() {
        super.setup()
        dao = testDatabase.getListDao()
    }

    @Test
    fun list_insertion_successful() = runBlockingTest {
        //GIVEN - tutto quello che ti serve per testare
        val listToInsert = ListEntity(
            title = "efficitur",
            description = null,
            updateDate = "perpetua"
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insert(listToInsert)

        //THEN - verifica se il test è andato come ti aspettavi
        val allLists = dao.getAllLists()
        assertTrue(allLists.size == 1)

    }

    @Test
    fun list_delete_successful() = runBlockingTest {
        //GIVEN - tutto quello che ti serve per testare
        val listToInsert = ListEntity(
            title = "omittam",
            description = null,
            updateDate = "tibique"
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insert(listToInsert)
        val listToDelete = dao.getAllLists()[0]
        dao.delete(listToDelete)

        //THEN - verifica se il test è andato come ti aspettavi
        val allLists = dao.getAllLists()
        assertTrue(allLists.isEmpty())
    }

    @Test
    fun list_update_successful() = runBlockingTest {
        //GIVEN - tutto quello che ti serve per testare
        val baseList = ListEntity(
            title = "penatibus",
            description = null,
            updateDate = "laoreet"
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insert(baseList)
        val updatedList = baseList.copy(description = "basedesc")
        dao.update(updatedList)

        //THEN - verifica se il test è andato come ti aspettavi
        assertFalse( dao.getAllLists()[0].description != null)

    }

}