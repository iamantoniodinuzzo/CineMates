package com.example.cinemates.api.response

import com.example.cinemates.model.Image

data class ActorImageResponse(
    val id: Int,
    val profiles: List<Image>
)