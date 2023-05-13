package com.example.cinemates.data.remote.response.credits


import kotlinx.serialization.Serializable

data class CreditsResponse(
    val cast: List<CastDTO> = listOf(),
    val crew: List<CrewDTO> = listOf(),
    val id: Int = 0
)