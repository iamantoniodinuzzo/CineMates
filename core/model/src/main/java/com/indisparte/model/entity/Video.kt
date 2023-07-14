package com.indisparte.model.entity


data class Video(
    val id: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val site: String,
    val type: String
)