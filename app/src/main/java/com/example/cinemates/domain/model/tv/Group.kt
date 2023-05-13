package com.example.cinemates.domain.model.tv


data class Group(
    val episodes: List<Episode>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)