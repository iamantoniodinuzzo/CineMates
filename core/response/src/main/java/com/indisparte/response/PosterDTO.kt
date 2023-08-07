package com.indisparte.response


import com.google.gson.annotations.SerializedName

data class PosterDTO(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    @SerializedName("file_path")
    val filePath: String,
    val height: Int,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val width: Int
)