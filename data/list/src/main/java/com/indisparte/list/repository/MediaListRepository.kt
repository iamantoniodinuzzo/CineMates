package com.indisparte.list.repository

import com.indisparte.list.MediaList
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 *@author Antonio Di Nuzzo
 */
interface MediaListRepository {

    fun addList(mediaList: MediaList): Flow<Result<Boolean>>
    fun removeList(mediaList: MediaList): Flow<Result<Boolean>>

    fun getAllList(): Flow<Result<List<MediaList>>>

    fun getListWithId(listId: Int): Flow<Result<MediaList?>>

}