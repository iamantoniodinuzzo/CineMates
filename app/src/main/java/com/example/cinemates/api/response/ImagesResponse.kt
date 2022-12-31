package com.example.cinemates.api.response

import com.example.cinemates.model.Image

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
class ImagesResponse(
    var id: Int,
    var backdrops: List<Image>,
    var posters: List<Image>
)