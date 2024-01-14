package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.indisparte.base.Media
import com.indisparte.base.MediaType

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "media", indices = [Index("mediaId")])
data class MediaEntity(
    @PrimaryKey val mediaId: Int,
    val mediaName: String,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double,
    val mediaType: Int,
)

fun MediaEntity.asDomain(): Media {
    return Media(
        id = this.mediaId,
        mediaName = this.mediaName,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        mediaType = MediaType.fromId(this.mediaType)
    )
}

fun Media.asEntity(): MediaEntity {
    return MediaEntity(
        mediaId = this.id,
        mediaName = this.mediaName,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        mediaType = this.mediaType.id,
    )
}