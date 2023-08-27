package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780


data class Backdrop(
    private val filePath: String,
    val height: Int,
    val width: Int,
) : TMDBItem() {
    val completePosterPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, filePath)

    val completePosterPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, filePath)

}