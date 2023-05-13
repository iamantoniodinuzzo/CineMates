package com.example.cinemates.data.remote.response.common

import com.google.gson.annotations.SerializedName

data class SpokenLanguageDTO(
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String
)