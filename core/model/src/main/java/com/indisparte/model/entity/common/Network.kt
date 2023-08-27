package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants


data class Network(
    val id: Int,
    private val logoPath: String,
    val name: String,
    val originCountry: String,
) : TMDBItem() {
    val formattedLogoPathW780: String?
        get() = getCompleteImagePath(
            Constants.IMAGE_BASE_URL_W780,
            logoPath
        )
    val formattedLogoPathW500: String?
        get() = getCompleteImagePath(
            Constants.IMAGE_BASE_URL_W500,
            logoPath
        )
}