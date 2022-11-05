package com.example.cinemates.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String,
    var isFavorite: Boolean
):Serializable