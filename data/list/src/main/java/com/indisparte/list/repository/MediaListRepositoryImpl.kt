package com.indisparte.list.repository

import com.indisparte.media_list.MediaList
import com.indisparte.list.source.local.MediaListLocalDataSource
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
/**
 *@author Antonio Di Nuzzo
 */
class MediaListRepositoryImpl
@Inject
constructor(
    private val mediaListLocalDataSource: MediaListLocalDataSource,
) : MediaListRepository {
    override fun addList(mediaList: MediaList): Flow<Result<Boolean>> = flow{
        emit(Result.Loading)
        val result = mediaListLocalDataSource.addList(mediaList)
        emit(Result.Success(result))
    }

    override fun removeList(mediaList: MediaList): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        val result = mediaListLocalDataSource.removeList(mediaList)
        emit(Result.Success(result))
    }

    override fun getAllList(): Flow<Result<List<MediaList>>> = flow {
        emit(Result.Loading)
        val result = mediaListLocalDataSource.getAllListByUserId()
        emit(Result.Success(result))
    }

    override fun getListWithId(listId: Int): Flow<Result<MediaList?>> = flow{
        emit(Result.Loading)
        val result = mediaListLocalDataSource.getListWithId(listId)
        emit(Result.Success(result))
    }
}