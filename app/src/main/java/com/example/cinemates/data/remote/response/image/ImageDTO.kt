package com.example.cinemates.data.remote.response.image

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
enum class ImageType(val value :Int){
    POSTER(0),BACKDROP(1)
}
data class ImageDTO(
    @SerializedName("file_path")
    val filePath: String,
    val height: Int,
    val width: Int
) {
    val imageType: ImageType
        get() = if (height > width) ImageType.POSTER else ImageType.BACKDROP

}