package com.indisparte.model.entity


class MovieDetails(
    adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    popularity: Double,
    posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    title: String,
    val video: Boolean,
    voteAverage: Double,
    val voteCount: Int,
) : Movie(
    adult = adult,
    id = id,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage
)