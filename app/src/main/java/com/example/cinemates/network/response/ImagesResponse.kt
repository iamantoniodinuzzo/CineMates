package com.example.cinemates.network.response

import com.example.cinemates.model.entities.Image

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
class ImagesResponse(
    var id: Int,
    var backdrops: List<Image>,
    var posters: List<Image>
)