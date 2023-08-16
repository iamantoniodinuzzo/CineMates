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
 * Converts a network response into a flow of [Result] that encapsulates the response data or error.
 *
 * @param T The type of the network response data.
 * @param O The type of the converted data.
 * @param request The suspend function that makes the network request.
 * @param mapper The function to map the network response data to a list of converted data.
 *
 * @return A [Flow] emitting [Result] objects representing the loading, success, or error states.
 *
 * @throws Exception if an error occurs during the network request or conversion process.
 *
 * @author Antonio Di Nuzzo
 */
suspend fun <T, O> getListFromResponse(
    request: suspend () -> Response<T>,
    mapper: (T) -> List<O>,
): Flow<Result<List<O>>> = flow {
    emit(Result.Loading) // Emit loading state
    try {
        val response = request.invoke()
        if (response.isSuccessful) {
            val responseData = response.body()
            if (responseData != null) {
                val mappedData = mapper(responseData)
                emit(Result.Success(mappedData)) // Emit success state with the retrieved movies
            } else {
                emit(Result.Error(Exception("Empty response"))) // Emit error state for empty response
            }
        } else {
            val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())

            emit(
                Result.Error(
                    Exception(
                        errorResponse?.message ?: "Unknown Error"
                    )
                )
            ) // Emit error state with the error message
        }
    } catch (e: Exception) {
        emit(Result.Error(e)) // Emit error state with the exception
    }
}

/**
 * Executes a network request using the provided [request] function and processes the response
 * to return a single object of type [O]. The response is converted to a [Flow] of [Result] which
 * represents different states of the data retrieval process.
 *
 * @param T The type of the network response.
 * @param O The type of the object to be mapped from the response.
 * @param request A suspend function that performs the network request.
 * @param mapper A function that maps the network response of type [T] to an object of type [O].
 *
 * @return A [Flow] of [Result] representing the different states of the data retrieval process.
 * Loading, Success, or Error states are emitted through the flow.
 */
suspend fun <T, O> getSingleFromResponse(
    request: suspend () -> Response<T>,
    mapper: (T) -> O,
): Flow<Result<O>> = flow {
    emit(Result.Loading) // Emit loading state
    try {
        val response = request.invoke()
        if (response.isSuccessful) {

            val data = response.body()
            if (data != null) {
                val mappedData = mapper(data)
                emit(Result.Success(mappedData)) // Emit success state with the retrieved movie details

            } else {
                emit(Result.Error(Exception("Empty response"))) // Emit error state for empty response
            }
        } else {
            val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())

            emit(
                Result.Error(
                    Exception(
                        errorResponse?.message ?: "Unknown Error"
                    )
                )
            ) // Emit error state with the error message
        }
    } catch (e: Exception) {
        emit(Result.Error(e)) // Emit error state with the exception
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
