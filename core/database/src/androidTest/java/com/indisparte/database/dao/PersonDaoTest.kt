package com.indisparte.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.FavoritePersonEntity
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class PersonDaoTest : BaseDaoTest() {

    private lateinit var dao: PersonDao

    @Before
    override fun setup() {
        super.setup()
        dao = testDatabase.getPersonDao()
    }

    @Test
    fun favPerson_insertion_successful() = runBlockingTest {
        //GIVEN - tutto quello che ti serve per testare
        val favPerson = FavoritePersonEntity(
            adult = false,
            gender = 6927,
            id = 2969,
            name = "Will Williams",
            profilePath = null,
            popularity = 2.3,
            department = "dissentiunt"
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        dao.insert(favPerson)

        //THEN - verifica se il test è andato come ti aspettavi
        val allFavPersons = dao.getAllFavoritePerson()
        assertTrue(allFavPersons.contains(favPerson))

    }

    @Test
    fun favPerson_update_successful() = runBlockingTest {
        //GIVEN - tutto quello che ti serve per testare
        val favPerson = FavoritePersonEntity(
            adult = false,
            gender = 1576,
            id = 5423,
            name = "Freddy McMahon",
            profilePath = null,
            popularity = 6.7,
            department = "regione"
        )
        dao.insert(favPerson)
        //WHEN - le azioni e i cambiamenti del soggetto del test
        val insertedPerson = dao.getAllFavoritePerson()[0]
        val updatedPerson = insertedPerson.copy(adult = true)
        dao.update(updatedPerson)

        //THEN - verifica se il test è andato come ti aspettavi
        assertTrue(dao.getAllFavoritePerson()[0].adult)
    }

}