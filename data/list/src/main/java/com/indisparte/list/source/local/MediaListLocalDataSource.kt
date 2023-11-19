package com.indisparte.list.source.local

import com.indisparte.database.dao.ListDao
import com.indisparte.media_list.MediaList
import com.indisparte.list.mapper.asDomain
import com.indisparte.list.mapper.asEntity
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
    private val dao: ListDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun addList(mediaList: MediaList): Boolean = withContext(ioDispatcher) {
        val entityList = mediaList.asEntity()
        val deferredInsertionResult = async {
            dao.insertList(entityList)
        }

        val result = deferredInsertionResult.await()

        return@withContext result > 0
    }

    suspend fun removeList(mediaList: MediaList): Boolean = withContext(ioDispatcher) {
        val entityList = mediaList.asEntity()
        val deferredInsertionResult = async {
            dao.deleteList(entityList)
        }
        val result = deferredInsertionResult.await()

        return@withContext result > 0
    }

    suspend fun getAllList(): List<MediaList> = withContext(ioDispatcher) {
        val deferredMediaList = async { dao.getAllLists() }
        return@withContext deferredMediaList.await().map { it.asDomain() }
    }

    suspend fun getListWithId(listId: Int): MediaList? = withContext(ioDispatcher) {
        val deferredResult = async { dao.getList(listId) }
        return@withContext deferredResult.await()?.asDomain()
    }

}