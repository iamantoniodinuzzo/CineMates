package com.example.cinemates.data.remote.response.tvShow


import kotlinx.serialization.Serializable

data class GroupDTO(
    val episodes: List<EpisodeDTO> ,
    val id: String ,
    val locked: Boolean ,
    val name: String ,
    val order: Int
)