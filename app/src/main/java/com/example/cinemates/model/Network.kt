package com.example.cinemates.model

import com.google.gson.annotations.SerializedName

data class Network(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String,
    val origin_country: String
)