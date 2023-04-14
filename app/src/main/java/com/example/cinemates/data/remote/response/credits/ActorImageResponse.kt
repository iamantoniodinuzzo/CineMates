package com.example.cinemates.data.remote.response.credits

import com.example.cinemates.data.remote.response.image.ImageDTO

data class ActorImageResponse(
    val id: Int,
    val profiles: List<ImageDTO>
)