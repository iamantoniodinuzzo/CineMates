package com.example.cinemates.domain

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Loading<out T>(val loadingState: Boolean) : Result<T>()
    data class Error(val message: String,val code:Int) : Result<Nothing>()
}
