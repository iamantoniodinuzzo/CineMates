package com.indisparte.movie_data.response


import com.google.gson.annotations.SerializedName

data class ReleaseDateDTO(
    val certification: String,
    val descriptors: List<String>,
    @SerializedName("iso_639_1")
    val iso6391: String,
    val note: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val type: Int
)