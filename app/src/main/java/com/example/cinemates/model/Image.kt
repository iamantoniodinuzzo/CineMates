package com.example.cinemates.model

import com.google.gson.annotations.SerializedName

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Image(
    @SerializedName("file_path")
    var filePath: String,
    var height: Int,
    var width: Int
) {
    val imageType: Int
        get() = if (height > width) POSTER else BACKDROP

    companion object {
        const val BACKDROP = 0
        const val POSTER = 1
    }
}