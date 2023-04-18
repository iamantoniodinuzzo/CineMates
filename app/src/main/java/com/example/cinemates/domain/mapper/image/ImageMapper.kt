package com.example.cinemates.domain.mapper.image

import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Image


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class ImageMapper : Mapper<ImageDTO, Image> {
    override fun map(input: ImageDTO): Image {
        return Image(
            filePath = input.filePath,
            imageType = input.imageType
        )
    }

}*/

fun ImageDTO.mapToImage():Image{
    return Image(
        filePath = this.filePath,
        imageType = this.imageType
    )
}