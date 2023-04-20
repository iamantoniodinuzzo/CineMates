package com.example.cinemates.data.remote.response.tvShow


import com.google.gson.annotations.SerializedName

enum class Type(val value: Int) {
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
    val network: NetworkDTO,
    private val type: Int
) {
    val episodeGroupType: Type
        get() {
            return when (type) {
                1 -> Type.OriginalAirDate
                2 -> Type.Absolute
                3 -> Type.DVD
                4 -> Type.Digital
                5 -> Type.StoryArc
                6 -> Type.Production
                7 -> Type.TV
                else -> {
                    throw UnsupportedOperationException()
                }
            }
        }
}