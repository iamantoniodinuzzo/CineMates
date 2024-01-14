package com.indisparte.list.source.local

import com.indisparte.base.Media
import com.indisparte.database.dao.ListDao
import com.indisparte.database.dao.UserDao
import com.indisparte.database.entity.asDomain
import com.indisparte.list.mapper.asDomain
import com.indisparte.list.mapper.asEntity
import com.indisparte.media_list.MediaList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class MediaListLocalDataSource
@Inject
constructor(
    private val listDao: ListDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun addList(mediaList: MediaList): Boolean = withContext(ioDispatcher) {
        val entityList = mediaList.asEntity()
        val insertionResult = listDao.insert(entityList)
        return@withContext insertionResult > 0
    }

    suspend fun removeList(mediaList: MediaList): Boolean = withContext(ioDispatcher) {
        val entityList = mediaList.asEntity()
        val deletionResult = listDao.delete(entityList)

        return@withContext deletionResult > 0
    }

    suspend fun getAllListByUserId(userId: Int): Map<MediaList, List<Media>> =
        withContext(ioDispatcher) {
            val result = userDao.getUserListsWithMedias(userId)[0]
            val mappedResult =
                result.lists.associate { listWithMedias -> listWithMedias.list.asDomain() to listWithMedias.medias.map { it.asDomain() } }
            return@withContext mappedResult
        }

    suspend fun getListWithId(listId: Int): MediaList? = withContext(ioDispatcher) {
        val deferredResult = async { listDao.getList(listId) }
        return@withContext deferredResult.await()?.asDomain()
    }

}