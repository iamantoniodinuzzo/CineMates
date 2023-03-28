package com.example.cinemates.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Genre(
    val id: Int,
    val name: String,
    var isFavorite: Boolean?=false
):Serializable