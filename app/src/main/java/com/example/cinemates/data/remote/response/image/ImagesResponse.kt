package com.example.cinemates.data.remote.response.image

import com.example.cinemates.data.remote.response.image.ImageDTO

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
class ImagesResponse(
    var id: Int,
    var backdrops: List<ImageDTO>,
    var posters: List<ImageDTO>
)