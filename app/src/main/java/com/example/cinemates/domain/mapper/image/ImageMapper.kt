package com.example.cinemates.domain.mapper.image

import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.domain.model.common.Image


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun ImageDTO.mapToImage(): Image {
    return Image(
        filePath = this.filePath,
        imageType = this.imageType
    )
}