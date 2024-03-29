package com.example.cinemates.domain.model.movie

import com.example.cinemates.domain.model.common.Media
import java.io.Serializable

data class Collection(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val parts: List<Media>,
    val posterPath: String
):Serializable