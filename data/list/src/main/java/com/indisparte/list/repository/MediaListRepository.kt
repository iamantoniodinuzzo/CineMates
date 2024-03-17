package com.indisparte.list.repository

import com.indisparte.base.Media
import com.indisparte.media_list.MediaList
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow

/**
 *@author Antonio Di Nuzzo
 */
interface MediaListRepository {

    fun addList(mediaList: MediaList): Flow<Result<Boolean>>
    fun removeList(mediaList: MediaList): Flow<Result<Boolean>>

    fun getAllListsByUserId(userId:Int): Flow<Result<Map<MediaList, List<Media>>>>

    fun getListWithId(listId: Int): Flow<Result<MediaList?>>

}