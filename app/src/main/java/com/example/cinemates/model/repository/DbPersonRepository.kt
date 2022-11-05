package com.example.cinemates.model.repository

import com.example.cinemates.model.entities.Person
import com.example.cinemates.model.local.dao.PersonDao
import com.example.cinemates.model.local.db.AppDatabase
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:23
 */
class DbPersonRepository
@Inject
constructor(appDatabase: AppDatabase) {
    companion object {
        private lateinit var personDao: PersonDao
    }

    init {
        personDao = appDatabase.personDao()
    }

    fun getPersons() = personDao.getAll()
    fun isPersonFavorite(id: Int) = personDao.isPersonFavorite(id)
    fun insertPerson(person: Person) = personDao.insert(person)
    fun updatePerson(person: Person) = personDao.update(person)
    fun deletePerson(person: Person) = personDao.delete(person)


}