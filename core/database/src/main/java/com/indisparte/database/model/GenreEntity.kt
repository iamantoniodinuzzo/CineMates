package com.indisparte.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indisparte.base.MediaType
import com.indisparte.common.Genre

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
    var isFavorite: Boolean,
    val mediaType: Int? = -1,
)

fun GenreEntity.asDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name,
        isFavorite = this.isFavorite,
        mediaType = this.mediaType?.let { MediaType.fromId(it) }
            ?: throw NullPointerException("Media type cannot be null")
    )
}

fun Genre.asEntity(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name,
        isFavorite = this.isFavorite,
        mediaType = mediaType?.id
    )
}
