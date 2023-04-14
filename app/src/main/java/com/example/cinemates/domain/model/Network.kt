package com.example.cinemates.domain.model

import com.google.gson.annotations.SerializedName

data class Network(
    val id: Int,
    val logoPath: String?,
    val name: String,
)