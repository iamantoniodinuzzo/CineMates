package com.indisparte.movie_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Genre
import com.indisparte.common.Video
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDatesByCountry
import com.indisparte.movie_data.findReleaseDateByCountry
import com.indisparte.movie_data.getLatestReleaseCertification
import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.movie_details.model.MovieInfoUiState
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import com.indisparte.network.util.succeeded
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@HiltViewModel
class MovieDetailsViewModel
@Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val LOG = Timber.tag(MovieDetailsViewModel::class.java.simpleName)

    private val _selectedMovie = MutableSharedFlow<Result<MovieDetails>>()
    val selectedMovie: SharedFlow<Result<MovieDetails>> get() = _selectedMovie


    /*Cast*/
    private val _cast = MutableStateFlow<Result<List<Cast>>>(Result.Success(emptyList()))
    val cast: StateFlow<Result<List<Cast>>> get() = _cast

    /*Similar movies*/
    private val _similarMovies = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val similarMovies: StateFlow<Result<List<Movie>>> get() = _similarMovies

    /*Collection parts*/
    private val _collectionParts = MutableStateFlow<Result<CollectionDetails>?>(null)
    val collectionParts: StateFlow<Result<CollectionDetails>?> get() = _collectionParts

    private val _movieInfo = MutableStateFlow<Result<MovieInfoUiState>>(Result.Loading)
    val movieInfo: StateFlow<Result<MovieInfoUiState>> get() = _movieInfo


    fun onDetailsFragmentReady(id: Int) {
        getMovieDetails(id)
        getCast(id)
        getSimilar(id)
    }


    fun updateGenre(genre: Genre) {
        viewModelScope.launch {
            genreRepository.updateSavedGenre(genre).first()
        }

    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Result.Loading)
            LOG.d("Loading state movie details...")
            try {
                movieRepository.getMovieDetailsAndUpdateWithLocalData(id)
                    .collectLatest { movieDetailsResult ->
                        LOG.d("Emit movie details result: $movieDetailsResult")
                        _selectedMovie.emit(movieDetailsResult)
                        //Check if operation succeeded
                        if (movieDetailsResult.succeeded) {
                            val movieDetails = (movieDetailsResult as Result.Success).data
                            LOG.d("Get details about ${movieDetails.title}..")
                            loadMovieInfo(movieDetails)
                            //Check if current movie is a part of collection
                            movieDetails.belongsToCollection?.let { collection ->
                                LOG.d("${movieDetails.title} is part of a Collection, retrieve collection details..")
                                getCollectionDetails(collection.id)
                            }
                        } else {
                            _selectedMovie.emit(Result.Error(CineMatesException.EmptyResponse))
                        }
                    }
            } catch (e: CineMatesException) {
                LOG.e("An error in get movie details: $e")
                _selectedMovie.emit(Result.Error(e))
            }
        }
    }


    private fun loadMovieInfo(
        movieDetailsResult: MovieDetails,
    ) {
        viewModelScope.launch {
            LOG.d("Try to load movie info's")
            try {
                val movieId = movieDetailsResult.id
                val country = Locale.getDefault().country
                combine(
                    movieRepository.getMovieDetailsAndUpdateWithLocalData(movieId),
                    movieRepository.getVideos(movieId),
                    movieRepository.getWatchProviders(movieId, country),
                    movieRepository.getCrew(movieId),
                    movieRepository.getReleaseDates(movieId),
                    movieRepository.getBackdrop(movieId),
                ) { (details, videos, watchProviders, crew, releaseDates, backdrops) ->
                    listOf(
                        details,
                        videos,
                        watchProviders,
                        crew,
                        releaseDates,
                        backdrops
                    )
                }.collectLatest { results ->
                    if (results.all { it is Result.Success }) {
                        LOG.d("All movie info's are a Result.Success")

                        val movieDetails = (results[0] as Result.Success<MovieDetails>).data
                        val videosResult = (results[1] as Result.Success<List<Video>>).data
                        val watchProvidersResult =
                            (results[2] as Result.Success<CountryResult>).data
                        val crewResult = (results[3] as Result.Success<List<Crew>>).data
                        val releaseDatesResult =
                            (results[4] as Result.Success<List<ReleaseDatesByCountry>>).data
                        val backdropsResult = (results[5] as Result.Success<List<Backdrop>>).data

                        val releaseDates =
                            releaseDatesResult.findReleaseDateByCountry(country) ?: emptyList()
                        val latestCertification = releaseDates.getLatestReleaseCertification()

                        val movieInfoUiState = MovieInfoUiState(
                            movieDetails,
                            videosResult,
                            watchProvidersResult,
                            releaseDates,
                            latestCertification,
                            backdropsResult,
                            crewResult,
                        )
                        LOG.d("Emit Movie Info UI State")
                        _movieInfo.emit(Result.Success(movieInfoUiState))
                    } else if (results.any { it is Result.Error }) {
                        val error: CineMatesException = (results.find { it is Result.Error }
                            ?: CineMatesException.GenericException) as CineMatesException
                        LOG.e("At least one movie info is not Result.Success, emit $error")
                        _movieInfo.emit(
                            Result.Error(error)
                        )
                    }

                }

            } catch (e: Exception) {
                LOG.e("Error in loadMovieInfo: ${e.localizedMessage}")
                _movieInfo.emit(
                    Result.Error(CineMatesException.GenericException)
                )
            }
        }

    }


    private fun getCollectionDetails(collectionId: Int) {
        viewModelScope.launch {
            _collectionParts.emit(Result.Loading)
            try {
                movieRepository.getCollectionDetails(collectionId)
                    .collectLatest { collectionDetails ->
                        _collectionParts.emit(collectionDetails)
                    }
            } catch (e: CineMatesException) {

                _collectionParts.emit(Result.Error(e))
            }
        }

    }

    private fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            _similarMovies.emit(Result.Loading)
            try {
                movieRepository.getSimilar(movieId).collectLatest { similar ->

                    _similarMovies.emit(similar)
                }
            } catch (e: CineMatesException) {

                _similarMovies.emit(Result.Error(e))
            }
        }

    }

    private fun getCast(movieId: Int) {
        viewModelScope.launch {
            _cast.emit(Result.Loading)
            try {
                movieRepository.getCast(movieId).collectLatest { cast ->
                    _cast.emit(cast)

                }
            } catch (e: CineMatesException) {
                _cast.emit(Result.Error(e))
            }
        }
    }


}

private operator fun <T> Array<T>.component6(): T = get(5)
