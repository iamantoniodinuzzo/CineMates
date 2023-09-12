package com.indisparte.common


data class Genre(
    val id: Int,
    val name: String,
    var isFavorite: Boolean = false
)