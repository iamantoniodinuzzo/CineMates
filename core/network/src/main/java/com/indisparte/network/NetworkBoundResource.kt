package com.indisparte.network

import com.indisparte.network.error.CineMatesExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


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








