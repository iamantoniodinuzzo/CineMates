package com.indisparte.model.entity

import com.indisparte.model.util.Constants


data class WatchProvider(
    val displayPriority: Int,
    private val logoPath: String?,
    val providerName: String
){
    val completeLogoPathW780: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$logoPath"

    val completeLogoPathW500: String?
        get() = if (logoPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W500}$logoPath"
}