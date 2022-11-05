package com.example.cinemates.network.response

import kotlinx.serialization.SerialName

/**
 * @author Antonio Di Nuzzo
 */
class GenericResponse<T>(
    val page: Int?,
    @SerialName(value = "total_pages")
    val totalPages: Int?,
    @SerialName(value = "total_results")
    val totalResults: Int?,
    val results: List<T> = listOf()
)