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
@Parcelize
open class ImageDTO(
    @SerializedName("file_path")
    var filePath: String,
    var height: Int,
    var width: Int
) :Parcelable{
    val imageType: ImageType
        get() = if (height > width) ImageType.POSTER else ImageType.BACKDROP


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImageDTO) return false

        if (filePath != other.filePath) return false

        return true
    }

    override fun hashCode(): Int {
        return filePath.hashCode()
    }

}