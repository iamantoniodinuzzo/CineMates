package com.indisparte.model.entity.tv


/**
 * Represents a group of episodes within an episode group of a TV show.
 *
 * @property episodes The list of [Episode] objects within this group.
 * @property id The unique ID associated with the group.
 * @property locked Indicates whether the group is locked or not.
 * @property name The name of the group.
 * @property order The order of the group within the episode group.
 */
data class Group(
    val episodes: List<Episode>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)