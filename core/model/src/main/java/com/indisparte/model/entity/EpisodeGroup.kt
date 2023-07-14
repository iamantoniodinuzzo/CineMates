package com.indisparte.model.entity

private enum class EpisodeGroupType(val value: Int, val type: String) {
    ORIGINAL_AIR_DATE(1, "Original air date"),
    ABSOLUTE(2, "Absolute"),
    DVD(3, "DVD"),
    DIGITAL(4, "Digital"),
    STORY_ARC(5, "Story arc"),
    PRODUCTION(6, "Production"),
    TV(7, "TV")
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
    val episodeGroupType: String?
        get() = EpisodeGroupType.values().find { it.value == type }?.name

}