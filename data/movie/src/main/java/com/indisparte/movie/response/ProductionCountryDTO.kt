package com.indisparte.movie.response


import com.google.gson.annotations.SerializedName

data class ProductionCountryDTO(
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String
)