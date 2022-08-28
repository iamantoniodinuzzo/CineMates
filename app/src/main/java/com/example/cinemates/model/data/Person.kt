package com.example.cinemates.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
open class Person(
    @Ignore
    val adult: Boolean,
    @Ignore
    val also_known_as: List<String>,
    @Ignore
    val biography: String?,
    @Ignore
    val birthday: String?,
    @Ignore
    val deathDay: String?,
    @Ignore
    val gender: Int?,
    @Ignore
    val homepage: String?,
    @PrimaryKey
    val id: Int,
    @Ignore
    val imdb_id: String?,
    @Ignore
    val known_for_department: String,
    val name: String,
    @Ignore
    val place_of_birth: String?,
    @Ignore
    val popularity: Double,
    val profile_path: String?,
    var isFavorite: Boolean = false
) : Serializable {
    @Ignore
    constructor(
        adult: Boolean,
        gender: Int?,
        id: Int,
        known_for_department: String,
        name: String,
        popularity: Double,
        profile_path: String?
    ) : this(
        adult, listOf(), null, "", "", gender, null, id, null,
        known_for_department, name, null, popularity, profile_path, false
    )

    constructor(
        id: Int,
        name: String,
        profile_path: String?,
        isFavorite: Boolean
    ) : this(
        false, arrayListOf(), null, null, null, null, null, id, null,
        "", name, null, 0.0, profile_path, isFavorite = isFavorite
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun setFavorite() {
        isFavorite = !isFavorite
    }

}