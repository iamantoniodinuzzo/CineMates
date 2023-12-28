package com.indisparte.tv

import com.indisparte.common.Genre
import com.indisparte.common.Network
import com.indisparte.common.ProductionCompany
import com.indisparte.common.ProductionCountry
import com.indisparte.common.SpokenLanguage

/**
 * Represents detailed information about a TV show.
 *
 * @property adult Indicates whether the TV show is intended for adult audiences.
 * @property backdropPath The backdrop image path for the TV show.
 * @property createdBy The list of individuals who created the TV show.
 * @property episodeRunTime The list of runtimes of episodes in minutes.
 * @property firstAirDate The date when the TV show first aired.
 * @property genres The list of genres associated with the TV show.
 * @property homepage The homepage URL of the TV show.
 * @property id The unique ID associated with the TV show.
 * @property inProduction Indicates whether the TV show is currently in production.
 * @property languages The list of languages available for the TV show.
 * @property lastAirDate The date of the last aired episode.
 * @property lastEpisodeToAir Information about the last aired episode.
 * @property name The name of the TV show.
 * @property networks The list of networks associated with the TV show.
 * @property nextEpisodeToAir Information about the next episode to air.
 * @property numberOfEpisodes The total number of episodes in the TV show.
 * @property numberOfSeasons The total number of seasons in the TV show.
 * @property originCountry The list of origin countries of the TV show.
 * @property originalLanguage The original language of the TV show.
 * @property originalName The original name of the TV show.
 * @property overview A brief overview of the TV show.
 * @property popularity The popularity of the TV show.
 * @property posterPath The poster path of the TV show.
 * @property productionCompanies The list of production companies involved in the TV show.
 * @property productionCountries The list of production countries for the TV show.
 * @property seasons The list of seasons in the TV show.
 * @property spokenLanguages The list of spoken languages in the TV show.
 * @property status The status of the TV show (e.g., "Ended", "In Production").
 * @property tagline The tagline of the TV show.
 * @property type The type of the TV show.
 * @property voteAverage The average vote rating for the TV show.
 * @property voteCount The total number of votes for the TV show.
 */
class TvShowDetails(
    val adult: Boolean,
    private val backdropPath: String?,
    val createdBy: List<CreatedBy>,
    private val episodeRunTime: List<Int>,
    private val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    private val lastAirDate: String,
    val lastEpisodeToAir: Episode,
    name: String,
    val networks: List<Network>,
    val nextEpisodeToAir: Episode?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    popularity: Double,
    posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    voteAverage: Double,
    val voteCount: Int,
) : TvShow(
    id,
    name,
    popularity,
    posterPath,
    voteAverage
) {
    val completeBackdropPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, backdropPath)
    val completeBackdropPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, backdropPath)

    val formattedListEpisodeRuntime: List<String>
        get() = episodeRunTime.map { formatRuntime(it) }

    val formattedAirDate: String?
        get() = formatDate(firstAirDate)
    val formattedLastAirDate: String?
        get() = formatDate(lastAirDate)

}