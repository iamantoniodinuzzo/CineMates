package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.indisparte.base.MediaType
import com.indisparte.common.Genre

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "genre", indices = [Index("genreId")])
data class GenreEntity(
    @PrimaryKey val genreId: Int,
    val name: String,
    val mediaType: Int,
)

fun GenreEntity.asDomain(): Genre {// FIXME: Is favorite è di default false
    return Genre(
        id = this.genreId,
        name = this.name,
        isFavorite = false,
        mediaType = this.mediaType.let { MediaType.fromId(it) }
    )
}

fun Genre.asEntity(): GenreEntity {
    return GenreEntity(
        genreId = this.id,
        name = this.name,
        mediaType = mediaType.id
    )
}
