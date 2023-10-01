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
    val mediaType: Int
)

fun MediaEntity.asDomain(): Media {
    return Media(
        id = this.id,
        mediaName = this.mediaName,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        mediaType = MediaType.fromId(this.mediaType)
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
    )
}