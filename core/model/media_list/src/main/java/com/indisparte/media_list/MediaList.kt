package com.indisparte.media_list

import java.util.Date

data class MediaList(
    val id: Int = 0,
    val title: String,
    val description: String?,
    var isPrivate: Boolean = true,
    val ownerId: Int,
    val creationDate: Date = Date(System.currentTimeMillis()),
    var updateDate: Date = creationDate,
)