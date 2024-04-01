package com.indisparte.actor.source.local

import com.indisparte.actor.mapper.asEntity
import com.indisparte.base.Person
import com.indisparte.database.dao.ActorDao
import com.indisparte.database.dao.UserDao
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import com.indisparte.person.Cast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class PeopleLocalDataSource
@Inject
constructor(
    private val actorDao: ActorDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun setActorAsFavorite(cast: Person):Boolean = withContext(ioDispatcher) {
        //Check if actor is in table
        val actor = actorDao.getActor(cast.id)
        if (actor == null) {
            //actor never been saved
            val savingResult = actorDao.insert(cast.asEntity())
            if (savingResult <= 0)
                return@withContext false

        }
        //Set actor as fav
        val favDate = Date(System.currentTimeMillis())
        // FIXME: Add user id from param or shared prefs
        val crossRefToInsert = UserFavActorCrossRef(actorId = cast.id, userId = 0, favDate =favDate)
        val insertionResult = userDao.insertUserFavActorCrossRef(crossRefToInsert)
        return@withContext insertionResult > 0
    }

    suspend fun removeFavoriteActor(actorId:Int, userId:Int):Boolean = withContext(ioDispatcher) {
        val crossRefToRemove = userDao.getUserFavActor(userId, actorId) ?: return@withContext false

        val deletionResult = userDao.deleteUserFavActorCrossRef(crossRefToRemove)

        return@withContext deletionResult > 0
    }

    suspend fun getUserFavActors(userId: Int):List<Cast> = withContext(ioDispatcher) {

        // TODO: Implement a good person domain class to do a mapping
        return@withContext emptyList()
    }


}