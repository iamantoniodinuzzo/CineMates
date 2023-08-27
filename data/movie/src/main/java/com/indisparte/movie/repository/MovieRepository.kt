package com.indisparte.movie.repository

import com.indisparte.model.entity.filter.MediaFilter
import com.indisparte.model.entity.filter.TimeWindow
import com.indisparte.model.entity.common.Backdrop
import com.indisparte.model.entity.person.Cast
import com.indisparte.model.entity.movie.CollectionDetails
import com.indisparte.model.entity.common.CountryResult
import com.indisparte.model.entity.person.Crew
import com.indisparte.model.entity.movie.Movie
import com.indisparte.model.entity.movie.MovieDetails
import com.indisparte.model.entity.movie.ReleaseDatesByCountry
import com.indisparte.model.entity.common.Video
import com.indisparte.movie.util.MovieListType
import com.indisparte.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieRepository {
     fun getByListType(movieListType: MovieListType): Flow<Result<List<Movie>>>
     fun getTrending(timeWindow: TimeWindow): Flow<Result<List<Movie>>>
     fun getBySearch(query: String): Flow<Result<List<Movie>>>
     fun getDiscoverable(mediaFilter: MediaFilter): Flow<Result<List<Movie>>>
     fun getDetails(movieId: Int): Flow<Result<MovieDetails>>
     fun getSimilar(movieId: Int): Flow<Result<List<Movie>>>
     fun getCast(movieId: Int): Flow<Result<List<Cast>>>
     fun getCrew(movieId: Int): Flow<Result<List<Crew>>>
     fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

     fun getVideos(movieId: Int): Flow<Result<List<Video>>>

     fun getReleaseDates(movieId: Int): Flow<Result<List<ReleaseDatesByCountry>>>

     fun getBackdrop(movieId: Int):Flow<Result<List<Backdrop>>>
     fun getCollectionDetails(collectionId: Int): Flow<Result<CollectionDetails>>

}