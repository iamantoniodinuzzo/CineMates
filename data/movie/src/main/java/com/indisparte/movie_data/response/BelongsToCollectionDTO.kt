package com.indisparte.movie_data.response


import com.google.gson.annotations.SerializedName

data class BelongsToCollectionDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String?
)