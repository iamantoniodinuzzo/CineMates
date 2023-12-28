package com.indisparte.tv

import androidx.annotation.StringRes


/**
 * Represents the type of an episode group.
 *
 * @property value The numeric value representing the episode group type.
 * @property typeResId The resource ID of the localized string describing the episode group type.
 * @author Antonio Di Nuzzo
 */
enum class EpisodeGroupType(val value: Int, @StringRes val typeResId: Int) {
    ORIGINAL_AIR_DATE(1, R.string.original_air_date),
    ABSOLUTE(2, R.string.absolute),
    DVD(3, R.string.dvd),
    DIGITAL(4, R.string.digital),
    STORY_ARC(5, R.string.story_arc),
    PRODUCTION(6, R.string.production),
    TV(7, R.string.tv);

    companion object {
        /**
         * Retrieves an [EpisodeGroupType] based on its numerical value.
         *
         * @param type The numerical value of the episode group type.
         * @return The corresponding [EpisodeGroupType] enum value.
         * @throws NoSuchElementException If no episode group type with the specified value is found.
         */
        @Throws(NoSuchElementException::class)
        fun fromValue(type: Int): EpisodeGroupType {
            return EpisodeGroupType.values().find { it.value == type }
                ?: throw NoSuchElementException("There is no Episode Group type with this value: $type")
        }
    }
}

/**
 * Represents a group of episodes within a TV show.
 *
 * @property description A brief description of the episode group.
 * @property episodeCount The total number of episodes in the group.
 * @property groupCount The total number of groups the episodes are divided into.
 * @property id The unique ID associated with the episode group.
 * @property name The name of the episode group.
 * @property network The network associated with the episode group.
 * @property type The type of the episode group as represented by [EpisodeGroupType].
 * @author Antonio Di Nuzzo
 */
open class EpisodeGroup(
    val description: String,
    val episodeCount: Int,
    val groupCount: Int,
    val id: String,
    val name: String,
    val network: String?,
    private val type: Int,
) {
    /**
     * Returns the [EpisodeGroupType] of the episode group.
     */
    val episodeGroupType: EpisodeGroupType
        get() = EpisodeGroupType.fromValue(type)

}