package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.common.Genre
import com.indisparte.database.entity.UserEntity

/**
 * @author Antonio Di Nuzzo
 */
data class UserWithFavGenres(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "genreId",
        associateBy = Junction(UserFavGenreCrossRef::class)
    )
    val genres: List<Genre>,
)
