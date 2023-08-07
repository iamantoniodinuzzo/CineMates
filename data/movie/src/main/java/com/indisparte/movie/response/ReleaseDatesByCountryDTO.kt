package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class ReleaseDatesByCountryDTO(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("release_dates")
    val releaseDates: List<ReleaseDateDTO>
)