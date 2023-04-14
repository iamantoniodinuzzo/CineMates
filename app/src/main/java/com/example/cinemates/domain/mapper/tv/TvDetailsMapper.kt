package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.domain.mapper.GenreMapper
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.mapper.ProductionCompaniesMapper
import com.example.cinemates.domain.mapper.ProductionCountryMapper
import com.example.cinemates.domain.model.TvShow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvDetailsMapper
@Inject
constructor(
    private val genreMapper: GenreMapper,
    private val productionCountryMapper: ProductionCountryMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val seasonMapper: SeasonMapper,
    private val episodeMapper: EpisodeMapper,
    private val networkMapper: NetworkMapper,
    private val createdByMapper: CreatedByMapper
) : Mapper<TvShowDTO, TvShow> {
    override fun map(input: TvShowDTO): TvShow {
        return TvShow(
            backdropPath = input.backdropPath,
            createdBy = input.createdBy.map { createdByMapper.map(it) },
            episodeRunTime = input.episodeRunTime,
            firstAirDate = input.formattedFirstAirDate,
            genres = input.genre.map { genreMapper.map(it) },
            homepage = input.homepage,
            id = input.id,
            inProduction = input.inProduction,
            languages = input.languages,
            lastAirDate = input.formattedLastAirDate,
            lastEpisodeToAir = input.lastEpisodeToAir?.let { episodeMapper.map(it) },
            name = input.name,
            network = input.network.map { networkMapper.map(it) },
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
            seasons = input.season.map { seasonMapper.map(it) },
            status = input.status,
            tagline = input.tagline,
            type = input.type,
            voteAverage = input.voteAverage
        )
    }

}