package com.example.cinemates.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cinemates.local.db.Converters

@Entity(tableName = "tv")
data class TvShow(
    @Ignore val backdrop_path: String? = null,
    @Ignore val created_by: List<CreatedBy>? = listOf(),
    @Ignore val episode_run_time: List<Int>? = listOf(),
    @Ignore val first_air_date: String? = null,
    @TypeConverters(Converters::class) val genres: List<Genre>,
    @Ignore val homepage: String? = null,
    @PrimaryKey val id: Int,
    @Ignore val in_production: Boolean? = null,
    @Ignore val languages: List<String>? = listOf(),
    @Ignore val last_air_date: String? = null,
    @Ignore val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String,
    @Ignore val networks: List<Network>? = listOf(),
    @Ignore val next_episode_to_air: Any? = null,
    @Ignore val number_of_episodes: Int? = null,
    @Ignore val number_of_seasons: Int? = null,
    @Ignore val origin_country: List<String>? = listOf(),
    @Ignore val original_language: String? = null,
    @Ignore val original_name: String? = null,
    @Ignore val overview: String? = null,
    @Ignore val popularity: Double? = null,
    val poster_path: String,
    @Ignore val production_companies: List<ProductionCompany>? = listOf(),
    @Ignore val production_countries: List<ProductionCountry>? = listOf(),
    @Ignore val seasons: List<Season>? = listOf(),
    @Ignore val spoken_languages: List<SpokenLanguage>? = listOf(),
    @Ignore val status: String? = null,
    @Ignore val tagline: String? = null,
    @Ignore val type: String? = null,
    val vote_average: Double,
    @Ignore val vote_count: Int? = null,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    var favorite: Boolean = false,
) : java.io.Serializable {

    constructor(
        genres: List<Genre>,
        id: Int,
        name: String,
        poster_path: String,
        vote_average: Double,
        personalStatus: PersonalStatus,
        favorite: Boolean
    ) : this(
        null,
        null,
        listOf(),
        null,
        genres,
        null,
        id,
        null,
        listOf(),
        null,
        null,
        name,
        listOf(),
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        poster_path,
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        null,
        null,
        null,
        vote_average,
        null,
        personalStatus,
        favorite
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TvShow) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}