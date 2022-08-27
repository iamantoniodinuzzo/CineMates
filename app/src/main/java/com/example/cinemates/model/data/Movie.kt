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
    var belongs_to_collection: Collection?,
    @TypeConverters(Converters::class)
    var genres: List<Genre>?,
    var homepage: String?,
    @PrimaryKey
    var id: Int,
    var imdb_id: String?,
    var original_language: String,
    var original_title: String,
    var overview: String?,
    var poster_path: String?,
    var release_date: String,
    var runtime: Int?,
    var title: String,
    var vote_average: Double,
    var personalStatus: PersonalStatus?,
    var favorite: Boolean?,
    @Ignore
    val backdrop_path: String?,
   /* @Ignore
    val spoken_languages: List<SpokenLanguage>,*/
    @Ignore
    val budget: Int,
    @Ignore
    val popularity: Double,
    @Ignore
    val adult: Boolean,
   /* @Ignore
    val production_companies: List<ProductionCompany>,
    @Ignore
    val production_countries: List<ProductionCountry>,*/
    @Ignore
    val revenue: Int,
    @Ignore
    val status: String?,
    @Ignore
    val tagline: String,
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
        "", 0, 0.0, false, 0, "", "", false, 0
    )

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (popularity != other.popularity) return false

        return true
    }
}

@TypeConverters(Converters::class)
enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}