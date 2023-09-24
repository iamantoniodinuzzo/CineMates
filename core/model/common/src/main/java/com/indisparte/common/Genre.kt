package com.indisparte.common

import com.indisparte.base.MediaType


data class Genre(
    val id: Int,
    val name: String,
    var isFavorite: Boolean = false,
    val mediaType: MediaType? = null,
)