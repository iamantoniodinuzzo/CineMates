package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.domain.mapper.GenreMapper
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.mapper.ProductionCompaniesMapper
import com.example.cinemates.domain.mapper.ProductionCountryMapper
import com.example.cinemates.domain.model.Movie
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieDetailsMapper
@Inject constructor(
    private val genreMapper: GenreMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val productionCountryMapper: ProductionCountryMapper,
    private val collectionMapper: CollectionMapper
) : Mapper<MovieDTO, Movie> {
    override fun map(input: MovieDTO): Movie {
        return Movie(
            belongsToCollection = input.belongsToCollection?.let { collectionMapper.map(it) },
            genres = input.genre.map { genreMapper.map(it) },
            id = input.id,
            posterPath = input.posterPath,
            releaseDate = input.formattedReleaseDate,
            runtime = input.formattedRuntime,
            title = input.title,
            voteAverage = input.voteAverage,
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
            homepage = input.homepage,
            backdropPath = input.backdropPath,
            overview = input.overview,
            budget = input.formattedBudget,
            popularity = input.popularity,
            adult = input.adult,
            revenue = input.formattedRevenue,
            status = input.status,
            tagline = input.tagline,
            video = input.video,
            productionCompanies = input.productionCompanies.map { productionCompaniesMapper.map(it) },
            productionCountries = input.productionCountries.map { productionCountryMapper.map(it) }
        )
    }

}