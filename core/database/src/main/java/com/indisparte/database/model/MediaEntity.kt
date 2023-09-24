package com.indisparte.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indisparte.base.Media
import com.indisparte.base.MediaType

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey val id: Int,
    val mediaName: String,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double,
    val mediaType: Int? = -1,
    var isFavorite: Boolean = false,
)

fun MediaEntity.asDomain(): Media {
    return Media(
        id = this.id,
        mediaName = this.mediaName,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        mediaType = this.mediaType?.let { MediaType.fromId(it) }
            ?: throw NullPointerException("Media type cannot be null"),
        isFavorite = this.isFavorite

    )
}

fun Media.asEntity(): MediaEntity {
    return MediaEntity(
        id = this.id,
        mediaName = this.mediaName,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        mediaType = this.mediaType.id,
        isFavorite = this.isFavorite
    )
}