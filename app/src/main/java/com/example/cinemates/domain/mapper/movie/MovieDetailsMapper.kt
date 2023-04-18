package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.example.cinemates.data.remote.response.movie.MovieDetailsDTO
import com.example.cinemates.domain.mapper.*
import com.example.cinemates.domain.model.Movie
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class MovieDetailsMapper
@Inject constructor(
    private val genreMapper: GenreMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val productionCountryMapper: ProductionCountryMapper,
    private val collectionMapper: CollectionMapper
) : Mapper<MovieDetailsDTO, Movie> {
    override fun map(input: MovieDetailsDTO): Movie {
        return Movie(
            belongsToCollection = input.belongsToCollection?.let { collectionMapper.map(it) },
            genres = input.genres.map { genreMapper.map(it) },
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

}*/

fun MovieDetailsDTO.mapToMovie():Movie{
    return Movie(
        belongsToCollection = this.belongsToCollection?.mapToCollection(),
        genres = this.genres.map { it.mapToGenre() },
        id = this.id,
        posterPath = this.posterPath,
        releaseDate = this.formattedReleaseDate,
        runtime = this.formattedRuntime,
        title = this.title,
        voteAverage = this.voteAverage,
        originalTitle = this.originalTitle,
        originalLanguage = this.originalLanguage,
        homepage = this.homepage,
        backdropPath = this.backdropPath,
        overview = this.overview,
        budget = this.formattedBudget,
        popularity = this.popularity,
        adult = this.adult,
        revenue = this.formattedRevenue,
        status = this.status,
        tagline = this.tagline,
        video = this.video,
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() }
    )
}