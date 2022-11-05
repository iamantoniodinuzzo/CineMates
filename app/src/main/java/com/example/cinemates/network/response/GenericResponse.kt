package com.example.cinemates.network.response

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
class GenericResponse<T>(
    val page: Int?,
    @SerialName(value = "total_pages")
    val total_pages: Int?,
    val total_results: Int?,
    val results: List<T> = listOf()
)