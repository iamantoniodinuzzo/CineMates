package com.indisparte.list.source.local

import com.indisparte.base.Media
import com.indisparte.database.dao.DefaultListDao
import com.indisparte.database.dao.UserDao
import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.asDomain
import com.indisparte.list.mapper.asDomain
import com.indisparte.list.mapper.asEntity
import com.indisparte.media_list.DefaultMediaList
import com.indisparte.media_list.DefaultTitle
import com.indisparte.media_list.MediaList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class MediaDefaultListLocalDataSource
@Inject
constructor(
    private val defaultListDao: DefaultListDao,
    private val userDao: UserDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun addUserDefaultLists(userId: Int): Boolean = withContext(ioDispatcher) {
        DefaultTitle.entries.forEach { defaultTitle ->
            val insertionValue = defaultListDao.insert(
                DefaultListEntity(
                    defaultTitle = defaultTitle.defaultTitle,
                    ownerId = userId
                )
            )
            if (insertionValue <= 0)
                return@withContext false

        }
        return@withContext true
    }

    suspend fun getAllDefaultListByUserId(userId: Int): Map<DefaultMediaList, List<Media>> =
        withContext(ioDispatcher) {
            val result = userDao.getUserDefaultListsWithMedias(userId)[0]
            val mappedResult =
                result.lists.associate { listWithMedias -> listWithMedias.list.asDomain() to listWithMedias.medias.map { it.asDomain() } }
            return@withContext mappedResult
        }

    suspend fun getDefaultListWithId(listId: Int): DefaultMediaList? = withContext(ioDispatcher) {
        val deferredResult = async { defaultListDao.getDefaultList(listId) }
        return@withContext deferredResult.await()?.asDomain()
    }

}