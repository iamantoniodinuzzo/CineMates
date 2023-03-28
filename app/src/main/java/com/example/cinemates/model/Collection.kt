package com.example.cinemates.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<Movie>,
    @SerializedName("poster_path")
    val posterPath: String
):Serializable