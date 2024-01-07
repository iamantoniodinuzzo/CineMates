package com.indisparte.network.util

import com.indisparte.network.exception.CineMatesException


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: CineMatesException) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

inline fun <reified T> Result<T>.whenResources(
    onSuccess: (T) -> Unit = {},
    onError: (CineMatesException) -> Unit = {},
    onLoading: () -> Unit = {},
) {
    when (this) {
        is Result.Success -> onSuccess(this.data)
        is Result.Error -> onError(this.exception)
        is Result.Loading -> onLoading()
    }
}

/**
 * `true` if [Result] is of type [Result.Success] & holds non-null [Result.Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null