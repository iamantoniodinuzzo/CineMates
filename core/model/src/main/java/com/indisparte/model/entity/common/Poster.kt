package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants


data class Poster(
    val aspectRatio: Double,
    private val filePath: String,
):TMDBItem(){
    val formattedFilePathW780:String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W780, filePath)
    val formattedFilePathW500: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W500, filePath)
}