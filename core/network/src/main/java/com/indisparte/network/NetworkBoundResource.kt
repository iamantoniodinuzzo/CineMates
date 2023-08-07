package com.indisparte.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()//from db

    val flow = if (shouldFetch(data)) {
        emit(Result.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Result.Error(throwable, it) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
}