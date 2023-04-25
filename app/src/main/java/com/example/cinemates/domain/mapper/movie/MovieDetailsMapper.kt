package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.data.remote.response.movie.MovieDetailsDTO
import com.example.cinemates.domain.mapper.mapToGenre
import com.example.cinemates.domain.mapper.mapToProductionCompany
import com.example.cinemates.domain.mapper.mapToProductionCountry
import com.example.cinemates.domain.model.Movie


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */

fun MovieDetailsDTO.mapToMovie(): Movie {
    return Movie(
        belongsToCollection = this.belongsToCollection?.mapToCollection(),
        genres = this.genres.map { it.mapToGenre() },
        id = this.id,
        posterPath = this.posterPath?:"",
        releaseDate = this.formattedReleaseDate,
        runtime = this.formattedRuntime,
        title = this.title,
        voteAverage = this.voteAverage,
        originalTitle = this.originalTitle,
        originalLanguage = this.originalLanguage,
        homepage = this.let { homepage } ?: "",
        backdropPath = this.backdropPath?:"",
        overview = this.overview,
        budget = this.formattedBudget,
        popularity = this.popularity,
        adult = this.adult,
        revenue = this.formattedRevenue,
        status = this.status,
        tagline = this.tagline?:"",
        video = this.video,
        productionCompanies = this.productionCompanies.map { it.mapToProductionCompany() },
        productionCountries = this.productionCountries.map { it.mapToProductionCountry() }
    )
}