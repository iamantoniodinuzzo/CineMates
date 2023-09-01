package com.indisparte.network


/**
 * @author Antonio Di Nuzzo
 */
/*
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()//from db

    val flow = if (shouldFetch(data)) {
        emit(Result.Loading)

        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (e: Exception) {
            query().map { Result.Error(e) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
}*/
