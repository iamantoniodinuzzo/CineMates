package com.indisparte.movie_data.response


import com.google.gson.annotations.SerializedName
import com.indisparte.response.GenreDTO
import com.indisparte.response.ProductionCompanyDTO
import com.indisparte.response.ProductionCountryDTO
import com.indisparte.response.SpokenLanguageDTO

data class MovieDetailsDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDTO?,
    val budget: Long,
    val genres: List<GenreDTO>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)