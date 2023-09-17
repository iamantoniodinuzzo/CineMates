package com.indisparte.common

enum class MediaType(val id: Int) {
    BOTH(0), MOVIE(1), TV(2);

    companion object {
        fun fromId(id: Int): MediaType {
            return values().firstOrNull { it.id == id }
                ?: throw NoSuchElementException("There is no media type with this id: $id")
        }
    }
}

data class Genre(
    val id: Int,
    val name: String,
    var isFavorite: Boolean = false,
    val mediaType: MediaType? = null,
)