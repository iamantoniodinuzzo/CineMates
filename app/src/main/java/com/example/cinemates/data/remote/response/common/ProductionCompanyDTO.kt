package com.example.cinemates.data.remote.response.common

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDTO(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originalCountry: String
)