package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.data.remote.response.tvShow.TvShowDetailsDTO
import com.example.cinemates.domain.mapper.*
import com.example.cinemates.domain.model.TvShow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class TvDetailsMapper
@Inject
constructor(
    private val genreMapper: GenreMapper,
    private val productionCountryMapper: ProductionCountryMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val seasonMapper: SeasonMapper,
    private val episodeMapper: EpisodeMapper,
    private val networkMapper: NetworkMapper,
    private val createdByMapper: CreatedByMapper
) : Mapper<TvShowDetailsDTO, TvShow> {
    override fun map(input: TvShowDetailsDTO): TvShow {
        return TvShow(
            backdropPath = input.backdropPath,
            createdBy = input.createdBy.map { createdByMapper.map(it) },
            episodeRunTime = input.formattedEpisodesRuntime,
            firstAirDate = input.formattedFirstAirDate,
            genres = input.genres.map { genreMapper.map(it) },
            homepage = input.homepage,
            id = input.id,
            inProduction = input.inProduction,
            languages = input.languages,
            lastAirDate = input.lastAirDate,
            lastEpisodeToAir = input.lastEpisodeToAir?.let { episodeMapper.map(it) },
            name = input.name,
            network = input.networks.map { networkMapper.map(it) },
            nextEpisodeToAir = input.nextEpisodeToAir?.let { episodeMapper.map(it) },
            numberOfEpisodes = input.numberOfEpisodes,
            numberOfSeasons = input.numberOfSeasons,
            originCountry = input.originCountry,
            originalLanguage = input.originalLanguage,
            originalName = input.originalName,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            productionCompanies = input.productionCompanies.map { productionCompaniesMapper.map(it) },
            productionCountries = input.productionCountries.map { productionCountryMapper.map(it) },
            seasons = input.seasons.map { seasonMapper.map(it) },
            status = input.status,
            tagline = input.tagline,
            type = input.type,
            voteAverage = input.voteAverage
        )
    }

}*/

fun TvShowDetailsDTO.mapToTvShow():TvShow{
    return TvShow(
        backdropPath = this.backdropPath,
        createdBy = this.createdBy.map { it.mapToCreatedBy()},
        episodeRunTime = this.formattedEpisodesRuntime,
        firstAirDate = this.formattedFirstAirDate,
        genres = this.genres.map { it.mapToGenre() },
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = this.lastEpisodeToAir?.mapToEpisode(),
        name = this.name,
        network = this.networks.map { it.mapToNetwork() },
        nextEpisodeToAir = this.nextEpisodeToAir?.mapToEpisode(),
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() },
        seasons = this.seasons.map { it.mapToSeason() },
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage
    )
}