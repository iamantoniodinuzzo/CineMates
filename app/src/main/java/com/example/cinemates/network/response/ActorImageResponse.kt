package com.example.cinemates.network.response

import android.provider.ContactsContract
import com.example.cinemates.model.entities.Image

data class ActorImageResponse(
    val id: Int,
    val profiles: List<Image>
)