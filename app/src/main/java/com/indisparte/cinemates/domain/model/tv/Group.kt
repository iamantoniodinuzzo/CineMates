package com.indisparte.cinemates.domain.model.tv


data class Group(
    val episodes: List<Episode>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)