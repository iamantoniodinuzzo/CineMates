package com.example.cinemates.data.remote.response.tvShow


import com.google.gson.annotations.SerializedName

enum class EpisodeGroupType(val value: Int) {
    OriginalAirDate(1), Absolute(2), DVD(3), Digital(4), StoryArc(5), Production(6), TV(7)
}

open class EpisodeGroupDTO(
    val description: String,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("group_count")
    val groupCount: Int ,
    val id: String ,
    val name: String,
    val network: NetworkDTO?,
    private val type: Int
) {
    val episodeGroupType: EpisodeGroupType
        get() {
            return when (type) {
                1 -> EpisodeGroupType.OriginalAirDate
                2 -> EpisodeGroupType.Absolute
                3 -> EpisodeGroupType.DVD
                4 -> EpisodeGroupType.Digital
                5 -> EpisodeGroupType.StoryArc
                6 -> EpisodeGroupType.Production
                7 -> EpisodeGroupType.TV
                else -> {
                    throw UnsupportedOperationException()
                }
            }
        }
}