package com.example.cinemates.data.remote.response.common

import com.google.gson.annotations.SerializedName

data class ProductionCountryDTO(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String
)