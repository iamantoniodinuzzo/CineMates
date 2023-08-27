package com.indisparte.model.entity.common

/**
 * Represents a video associated with media content.
 *
 * @property id The unique identifier of the video.
 * @property key The key or code of the video, used for embedding.
 * @property name The name or title of the video.
 * @property official Indicates whether the video is official.
 * @property site The website where the video is hosted.
 * @property type The type or category of the video.
 * @author Antonio Di Nuzzo
 */
data class Video(
    val id: String,
    private val key: String,
    val name: String,
    val official: Boolean,
    val site: String,
    val type: String,
) {
    /**
     * Gets the complete YouTube video thumbnail URL.
     *
     * @return The complete URL of the YouTube video thumbnail if the video is hosted on YouTube,
     * or null otherwise.
     */
    val completeYoutubeVideoPath: String?
        get() {
            return if (site.lowercase() == "youtube")
                "https://img.youtube.com/vi/$key/0.jpg"
            else
                null
        }
}
