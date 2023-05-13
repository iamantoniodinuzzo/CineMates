package com.example.cinemates.data.remote.response.image

import com.example.cinemates.data.remote.response.image.ImageDTO

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
class ImagesResponse(
    val id: Int,
    val backdrops: List<ImageDTO>,
    val posters: List<ImageDTO>
)