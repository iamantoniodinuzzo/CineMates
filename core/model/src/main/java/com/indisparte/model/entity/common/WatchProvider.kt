package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780


data class WatchProvider(
    val displayPriority: Int,
    private val logoPath: String?,
    val providerName: String
):TMDBItem(){
    val completeLogoPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780,logoPath)

    val completeLogoPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500,logoPath)
}