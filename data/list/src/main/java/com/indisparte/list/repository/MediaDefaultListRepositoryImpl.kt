package com.indisparte.list.repository

import com.indisparte.base.Media
import com.indisparte.list.source.local.MediaDefaultListLocalDataSource
import com.indisparte.media_list.MediaList
import com.indisparte.list.source.local.MediaListLocalDataSource
import com.indisparte.media_list.DefaultMediaList
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
/**
 *@author Antonio Di Nuzzo
 */
class MediaDefaultListRepositoryImpl
@Inject
constructor(
    private val mediaDefaultListLocalDataSource: MediaDefaultListLocalDataSource,
) : MediaDefaultListRepository {
    override fun addDefaultLists(userId: Int): Flow<Result<Boolean>> = flow{
        emit(Result.Loading)
        val result = mediaDefaultListLocalDataSource.addUserDefaultLists(userId)
        emit(Result.Success(result))
    }

    override fun getAllDefaultListsByUserId(userId:Int): Flow<Result<Map<DefaultMediaList, List<Media>>>> = flow {
        emit(Result.Loading)
        val result = mediaDefaultListLocalDataSource.getAllDefaultListByUserId(userId)
        emit(Result.Success(result))
    }

    override fun getListWithId(listId: Int): Flow<Result<DefaultMediaList?>> = flow{
        emit(Result.Loading)
        val result = mediaDefaultListLocalDataSource.getDefaultListWithId(listId)
        emit(Result.Success(result))
    }
}