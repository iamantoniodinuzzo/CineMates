package com.indisparte.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber


/**
 * Fetches data either from a local source or a remote source, depending on availability.
 *
 * This function is used to retrieve a list of items, attempting to fetch them from a local source first.
 * If the local data is available and not empty, it's emitted as a success result. If local data is
 * not available or empty, it fetches the data from a remote source and saves it locally before emitting
 * it as a success result.
 *
 * @param T The type of items in the list.
 * @param localFetch A suspend function that fetches data from a local source.
 * @param remoteFetch A suspend function that fetches data from a remote source and returns it as a Flow of Result.
 * @param saveToLocal A suspend function that saves data to the local source.
 *
 * @return A Flow of Result that emits the fetched data or error states.
 * @author Antonio Di Nuzzo
 */
inline fun <T> fetchFromLocalOrRemote(
    crossinline localFetch: suspend () -> List<T?>?,
    crossinline remoteFetch: suspend () -> Flow<Result<List<T>>>,
    crossinline saveToLocal: suspend (List<T>) -> Unit,
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
//                    emit(Result.Success(remoteResult.data))
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
        // Emit the local data if available
        if (localData.isNullOrEmpty()) {
            val updatedLocalData = localFetch.invoke()
            if (!updatedLocalData.isNullOrEmpty()) {
                emit(Result.Success(updatedLocalData.filterNotNull()))
            }
        }
    }
}












