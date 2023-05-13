package com.example.cinemates.domain.model.tv


import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupType


data class EpisodeGroupDetails(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val groups: List<Group>,
    val id: String,
    val name: String,
    val network: Network?,
    val episodeGroupType: EpisodeGroupType
)