package com.indisparte.network


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}
inline fun <reified T> Resource<T>.whenResources(
    onSuccess: (T?) -> Unit = {},
    onError: (Throwable?) -> Unit = {},
    onLoading: () -> Unit = {}
) {
    when (this) {
        is Resource.Success -> onSuccess(this.data)
        is Resource.Error -> onError(this.error)
        is Resource.Loading -> onLoading()
    }
}