package com.example.cinemates.model


data class TvShow(
    val backdrop_path: String? = null,
    val created_by: List<CreatedBy>? = listOf(),
    val episode_run_time: List<Int>? = listOf(),
    val first_air_date: String? = null,
     val genres: List<Genre>,
    val homepage: String? = null,
    val id: Int,
    val in_production: Boolean? = null,
    val languages: List<String>? = listOf(),
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String,
    val networks: List<Network>? = listOf(),
    val next_episode_to_air: Any? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String>? = listOf(),
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String,
    val production_companies: List<ProductionCompany>? = listOf(),
    val production_countries: List<ProductionCountry>? = listOf(),
    val seasons: List<Season>? = listOf(),
    val spoken_languages: List<SpokenLanguage>? = listOf(),
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double,
    val vote_count: Int? = null,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    var favorite: Boolean = false,
) : java.io.Serializable {

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