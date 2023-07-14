package com.indisparte.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
suspend fun <T, O> getListFromResponse(
    request: suspend () -> Response<T>,
    mapper: (T) -> List<O>,
): Flow<Resource<List<O>>> = flow {
    emit(Resource.Loading()) // Emit loading state

    try {
        val response = request.invoke()
        if (response.isSuccessful) {
            val responseData = response.body()
            if (responseData != null) {
                val movies = mapper(responseData)
                emit(Resource.Success(movies)) // Emit success state with the retrieved movies
            } else {
                emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody ?: response.message()
            emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
        }
    } catch (e: Exception) {
        emit(Resource.Error(e)) // Emit error state with the exception
    }
}

suspend fun <T, O> getSingleFromResponse(
    request: suspend () -> Response<T>,
    mapper: (T) -> O,
): Flow<Resource<O>> = flow {
    emit(Resource.Loading()) // Emit loading state

    try {
        val response = request.invoke()
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                val movieDetails = mapper(data)
                emit(Resource.Success(movieDetails)) // Emit success state with the retrieved movie details
            } else {
                emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody ?: response.message()
            emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
        }
    } catch (e: Exception) {
        emit(Resource.Error(e)) // Emit error state with the exception
    }
}
