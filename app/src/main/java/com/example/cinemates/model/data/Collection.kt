package com.example.cinemates.model.data

import java.io.Serializable

data class Collection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<Movie>,
    val poster_path: Any
):Serializable