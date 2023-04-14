package com.example.cinemates.domain.model

import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val parts: List<Movie>,
    val posterPath: String
):Serializable