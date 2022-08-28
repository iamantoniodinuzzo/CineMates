package com.example.cinemates.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cinemates.util.Converters
import java.io.Serializable

@Entity
data class Movie(
    @TypeConverters(Converters::class)
    val belongs_to_collection: Collection?,
    @TypeConverters(Converters::class)
    val genres: List<Genre>?,
    val homepage: String?,
    @PrimaryKey
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val runtime: Int?,
    val title: String,
    val vote_average: Double,
    val personalStatus: PersonalStatus?,
    val favorite: Boolean?,
    @Ignore
    val backdrop_path: String?,
    @Ignore
    val budget: Int,
    @Ignore
    val popularity: Double,
    @Ignore
    val adult: Boolean,
    @Ignore
    val revenue: Int,
    @Ignore
    val status: String?,
    @Ignore
    val tagline: String?,
    @Ignore
    val video: Boolean,
    @Ignore
    val vote_count: Int
) : Serializable {
    constructor(
        genres: List<Genre>,
        belongs_to_collection: Collection,
        homepage: String,
        id: Int,
        imdb_id: String,
        original_language: String,
        original_title: String,
        overview: String,
        poster_path: String,
        release_date: String,
        runtime: Int,
        title: String,
        vote_average: Double,
        personalStatus: PersonalStatus,
        favorite: Boolean
    ) : this(
        belongs_to_collection, genres, homepage, id, imdb_id, original_language, original_title,
        overview, poster_path, release_date, runtime, title, vote_average, personalStatus, favorite,
        null, 0, 0.0, false, 0, null, null, false, 0
    )

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false

        return true
    }


}

@TypeConverters(Converters::class)
enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}