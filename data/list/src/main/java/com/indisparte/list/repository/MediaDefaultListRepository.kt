package com.indisparte.list.repository

import com.indisparte.base.Media
import com.indisparte.media_list.DefaultMediaList
import com.indisparte.media_list.MediaList
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow

/**
 *@author Antonio Di Nuzzo
 */
interface MediaDefaultListRepository {

    fun addDefaultLists(userId: Int): Flow<Result<Boolean>>

    fun getAllDefaultListsByUserId(userId:Int): Flow<Result<Map<DefaultMediaList, List<Media>>>>

    fun getListWithId(listId: Int): Flow<Result<DefaultMediaList?>>

}