package com.indisparte.movie.util

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo
 */
class GenericResponse<T>(
    val page: Int?,
    @SerializedName(value = "total_pages")
    val totalPages: Int?,
    @SerializedName(value = "total_results")
    val totalResults: Int?,
    val results: List<T> = listOf()
)