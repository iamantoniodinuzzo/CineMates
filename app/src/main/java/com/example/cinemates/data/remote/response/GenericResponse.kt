package com.example.cinemates.data.remote.response

import kotlinx.serialization.SerialName

/**
 * @author Antonio Di Nuzzo
 */
abstract class GenericResponse<T>(
    val page: Int?,
    @SerialName(value = "total_pages")
    val totalPages: Int?,
    @SerialName(value = "total_results")
    val totalResults: Int?,
    val results: List<T> = listOf()
)