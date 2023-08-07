package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName
import com.indisparte.response.MovieDTO

data class CollectionDetailsResponseDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int,
    val name: String,
    val overview: String?,
    val parts: List<MovieDTO>,
    @SerializedName("poster_path")
    val posterPath: String,
)