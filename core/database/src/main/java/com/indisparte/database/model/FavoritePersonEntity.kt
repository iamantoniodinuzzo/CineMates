package com.indisparte.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indisparte.person.Person

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "person")
data class FavoritePersonEntity(
    val adult: Boolean,
    val gender: Int,
    @PrimaryKey val id: Int,
    val name: String,
    val profilePath: String?,
    val popularity: Double,
    val department: String,
)

fun FavoritePersonEntity.asDomain(): Person {
    return Person(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.department,
        name = this.name,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun Person.asEntity(): FavoritePersonEntity {
    return FavoritePersonEntity(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath,
        popularity = this.popularity,
        department = this.knownForDepartment

    )
}
