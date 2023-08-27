package com.indisparte.model.entity

import com.indisparte.model.util.Constants


data class Poster(
    val aspectRatio: Double,
    val filePath: String,
){
    val formattedFilePath:String
        get() {
            return "${Constants.IMAGE_BASE_URL_W780}$filePath"
        }
}