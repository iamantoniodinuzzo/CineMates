package com.example.cinemates.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cinemates.util.Converters
import java.io.Serializable
import java.util.*

/**
 * @author Antonio Di Nuzzo
 * Created 31/05/2022 at 11:32
 */
@Entity
open class Person : Serializable {
    var name: String
    var profilePath: String

    @PrimaryKey
    var id: Int
    var isFavorite = false

    @TypeConverters(Converters::class)
    var popularity: Number

    @Ignore
    constructor(name: String, profile_path: String, id: Int, popularity: Number) {
        this.name = name
        this.profilePath = profile_path
        this.id = id
        this.popularity = popularity
    }

    constructor(
        name: String,
        profile_path: String,
        id: Int,
        popularity: Number,
        favorite: Boolean
    ) {
        this.name = name
        this.profilePath = profile_path
        this.id = id
        this.popularity = popularity
        isFavorite = favorite
    }

    fun setFavorite() {
        isFavorite = !isFavorite
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        val person = other
        return name == person.name && id == person.id
    }

    override fun hashCode(): Int {
        return Objects.hash(name, id)
    }

    override fun toString(): String {
        return "Person{" +
                "name='" + name + '\'' +
                ", profile_path='" + profilePath + '\'' +
                ", id=" + id +
                ", popularity=" + popularity +
                '}'
    }
}