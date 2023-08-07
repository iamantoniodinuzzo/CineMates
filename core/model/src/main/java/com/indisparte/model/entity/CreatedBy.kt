package com.indisparte.model.entity

import com.indisparte.model.util.Constants


data class CreatedBy(
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    private val profilePath: String?,
) {
    val completeProfilePathW780: String?
        get() = if (profilePath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$profilePath"

    val completeProfilePathW500: String?
        get() = if (profilePath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W500}$profilePath"
}