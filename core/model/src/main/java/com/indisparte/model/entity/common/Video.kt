package com.indisparte.model.entity.common


data class Video(
    val id: String,
    private val key: String,
    val name: String,
    val official: Boolean,
    val site: String,
    val type: String,
) {
    val completeYoutubeVideoPath: String?
        get() {
            return if (site.lowercase() == "youtube")
                "https://img.youtube.com/vi/$key/0.jpg"
            else
                null
        }

}