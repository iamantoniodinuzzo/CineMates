package com.indisparte.network

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo
 */
data class GenericResponse<T>(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    val results: List<T> = listOf()
)