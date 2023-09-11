package com.indisparte.common


data class Genre(
    val id: Int,
    val name: String,
    val isFavorite: Boolean = false
)