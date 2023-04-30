package com.example.cinemates.domain.model.common

import android.os.Parcelable
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.image.ImageType
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class Image(
    val filePath: String,
    val imageType: ImageType
)