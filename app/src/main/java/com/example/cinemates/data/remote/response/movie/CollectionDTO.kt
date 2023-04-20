package com.example.cinemates.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class CollectionDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<MovieDTO>?,
    @SerializedName("poster_path")
    val posterPath: String?
)