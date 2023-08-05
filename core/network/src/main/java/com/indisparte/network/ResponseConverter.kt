package com.indisparte.network

import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.reflect.Type


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
            val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())

            emit(
                Resource.Error(
                    Exception(
                        errorResponse?.message ?: "Unknown Error"
                    )
                )
            ) // Emit error state with the error message//
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
    Timber.tag("ResponseConverter").d("Loading...")
    try {
        val response = request.invoke()
        if (response.isSuccessful) {
            Timber.tag("ResponseConverter").d("Successful response")

            val data = response.body()
            if (data != null) {
                Timber.tag("ResponseConverter").d("Data is not null..")

                val movieDetails = mapper(data)
                Timber.tag("ResponseConverter").d("Emit movie details: $movieDetails")
                emit(Resource.Success(movieDetails)) // Emit success state with the retrieved movie details

            } else {
                Timber.tag("ResponseConverter").d("Empty response")
                emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
            }
        } else {
            val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())

            Timber.tag("ResponseConverter").e("Error: ${errorResponse?.message} ")

            emit(
                Resource.Error(
                    Exception(
                        errorResponse?.message ?: "Unknown Error"
                    )
                )
            ) // Emit error state with the error message
        }
    } catch (e: Exception) {
        emit(Resource.Error(e)) // Emit error state with the exception
    }
}

private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
    return try {
        val gson = Gson()
        val errorBodyString = errorBody?.string()
        val type: Type = object : TypeToken<ErrorResponse>() {}.type
        gson.fromJson(errorBodyString, type)
    } catch (exception: Exception) {
        null
    }
}
