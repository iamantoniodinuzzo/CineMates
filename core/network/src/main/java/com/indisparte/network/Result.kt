package com.indisparte.network


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class Result<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Result<T>(data, throwable)
}
inline fun <reified T> Result<T>.whenResources(
    onSuccess: (T?) -> Unit = {},
    onError: (Throwable?) -> Unit = {},
    onLoading: () -> Unit = {}
) {
    when (this) {
        is Result.Success -> onSuccess(this.data)
        is Result.Error -> onError(this.error)
        is Result.Loading -> onLoading()
    }
}