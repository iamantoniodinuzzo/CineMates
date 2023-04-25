package com.example.cinemates.data.remote.response.tvShow

import com.google.gson.annotations.SerializedName

data class NetworkDTO(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)