package com.example.cinemates.domain.model.tv

import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupType


data class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: Network?,
    val episodeGroupType: EpisodeGroupType
):java.io.Serializable