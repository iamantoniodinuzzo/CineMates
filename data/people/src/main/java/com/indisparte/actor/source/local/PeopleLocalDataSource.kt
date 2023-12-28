package com.indisparte.actor.source.local

import com.indisparte.database.dao.PersonDao
import com.indisparte.database.entity.asDomain
import com.indisparte.database.entity.asEntity
import com.indisparte.base.Person
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class PeopleLocalDataSource
@Inject
constructor(
    private val dao: PersonDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun insertFavoritePerson(person: Person) = withContext(ioDispatcher) {
        val entity = person.asEntity()
        val deferredInsertionResult = async {
            dao.insert(entity)
        }
        return@withContext deferredInsertionResult.await()
    }

    suspend fun removeFavoritePerson(person: Person) = withContext(ioDispatcher) {
        val entity = person.asEntity()
        val deferredDeletionResult = async {
            dao.delete(entity)
        }
        return@withContext deferredDeletionResult.await()
    }

    suspend fun getAllFavoritePerson() = withContext(ioDispatcher) {
        val allFavoritePersonEntities = async { dao.getAllFavoritePerson() }.await()
        val allFavoritePersonAsDomain = allFavoritePersonEntities.map { it.asDomain() }
        return@withContext allFavoritePersonAsDomain
    }

    suspend fun isFavoritePerson(personId: Int) = withContext(ioDispatcher) {
        val deferredBoolean = async { dao.isPersonFavorite(personId) }
        return@withContext deferredBoolean.await()
    }

}