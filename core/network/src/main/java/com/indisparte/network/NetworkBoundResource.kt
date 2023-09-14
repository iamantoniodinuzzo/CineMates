package com.indisparte.network

import com.indisparte.network.error.CineMatesExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber


/**
 * @author Antonio Di Nuzzo
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    val data = query().first()//from db

    val flow = if (shouldFetch(data)) {
        emit(Result.Loading)

        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (e: CineMatesExceptions) {
            query().map { Result.Error(e) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
}

inline fun <T> fetchFromLocalOrRemote(
    crossinline localFetch: suspend () -> List<T?>?,
    crossinline remoteFetch: suspend () -> Flow<Result<List<T>>>,
    crossinline saveToLocal: suspend (List<T>) -> Unit
): Flow<Result<List<T>>> = flow {
    val localData = localFetch.invoke()

    if (!localData.isNullOrEmpty()) {
        Timber.tag("Repository").d("Emit data from local source")
        emit(Result.Success(localData.filterNotNull()))
    } else {
        Timber.tag("Repository").d("Fetching data from remote source")
        val remoteFlow = remoteFetch.invoke()

        remoteFlow.collect { remoteResult ->
            when (remoteResult) {
                is Result.Success -> {
                    Timber.tag("Repository").d("Saving data to local source")
                    saveToLocal(remoteResult.data) // Save data locally
                    emit(Result.Success(remoteResult.data))
                }

                is Result.Error -> {
                    // Handle remote error
                    emit(Result.Error(remoteResult.exception))
                }

                is Result.Loading -> {
                    // Handle loading state if needed
                    emit(Result.Loading)
                }
            }
        }
    }
}












