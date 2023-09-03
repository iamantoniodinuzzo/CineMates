package com.indisparte.movie_details.model

import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.person.Crew

/**
 *@author Antonio Di Nuzzo
 */
data class MovieInfoUiState(
    val movieDetails: MovieDetails,
    val videos: List<Video>,
    val watchProvider: CountryResult? = null,
    val releaseDates: List<ReleaseDate>,
    val latestCertification: String? = null,
    val backdrops: List<Backdrop>,
    val crew : List<Crew>
)
