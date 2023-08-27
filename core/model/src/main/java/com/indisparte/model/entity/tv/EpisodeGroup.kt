package com.indisparte.model.entity.tv

import androidx.annotation.StringRes
import com.indisparte.model.R

enum class EpisodeGroupType(val value: Int, @StringRes val typeResId: Int) {
    ORIGINAL_AIR_DATE(1, R.string.original_air_date),
    ABSOLUTE(2, R.string.absolute),
    DVD(3, R.string.dvd),
    DIGITAL(4, R.string.digital),
    STORY_ARC(5, R.string.story_arc),
    PRODUCTION(6, R.string.production),
    TV(7, R.string.tv)
}


open class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: String?,
    private val type: Int,
) {
    val episodeGroupType: EpisodeGroupType?
        get() = EpisodeGroupType.values().find { it.value == type }

}