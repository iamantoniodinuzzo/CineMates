package com.indisparte.movie_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.model.entity.common.Backdrop
import com.indisparte.model.entity.person.Cast
import com.indisparte.model.entity.movie.CollectionDetails
import com.indisparte.model.entity.common.CountryResult
import com.indisparte.model.entity.person.Crew
import com.indisparte.model.entity.movie.Movie
import com.indisparte.model.entity.movie.MovieDetails
import com.indisparte.model.entity.movie.ReleaseDate
import com.indisparte.model.entity.common.Video
import com.indisparte.model.entity.movie.findReleaseDateByCountry
import com.indisparte.model.entity.movie.getLatestReleaseCertification
import com.indisparte.movie.repository.MovieRepository
import com.indisparte.network.Result
import com.indisparte.network.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
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
) : ViewModel() {

    private val LOG = Timber.tag("MovieDetailsViewModel")

    private val _selectedMovie = MutableSharedFlow<Result<MovieDetails>>()
    val selectedMovie: SharedFlow<Result<MovieDetails>> get() = _selectedMovie.asSharedFlow()
    private val _videos = MutableStateFlow<Result<List<Video>>?>(null)
    val videos: StateFlow<Result<List<Video>>?> get() = _videos
    private val _cast = MutableStateFlow<Result<List<Cast>>?>(null)
    val cast: StateFlow<Result<List<Cast>>?> get() = _cast

    private val _similarMovies = MutableStateFlow<Result<List<Movie>>?>(null)
    val similarMovies: StateFlow<Result<List<Movie>>?> get() = _similarMovies
    private val _watchProviders = MutableStateFlow<Result<CountryResult?>>(Result.Success(null))
    val watchProviders: StateFlow<Result<CountryResult?>> get() = _watchProviders
    private val _crew = MutableStateFlow<Result<List<Crew>>?>(null)
    val crew: StateFlow<Result<List<Crew>>?> get() = _crew
    private val _releaseDates = MutableStateFlow<Result<List<ReleaseDate>>?>(null)
    val releaseDates: StateFlow<Result<List<ReleaseDate>>?> get() = _releaseDates
    private val _latestCertification = MutableStateFlow<String?>(null)
    val latestCertification: StateFlow<String?> get() = _latestCertification

    private val _backdrops = MutableStateFlow<Result<List<Backdrop>>?>(null)
    val backdrops: StateFlow<Result<List<Backdrop>>?> get() = _backdrops

    private val _collectionParts = MutableStateFlow<Result<CollectionDetails>?>(null)
    val collectionParts: StateFlow<Result<CollectionDetails>?> get() = _collectionParts

    init {
        observeSelectedMovie()
    }

    fun onDetailsFragmentReady(id: Int) = getMovieDetails(id)


    private fun observeSelectedMovie() {
        viewModelScope.launch {
            selectedMovie.filter { it.succeeded }.map { it as Result.Success }
                .mapNotNull { it.data }.distinctUntilChanged().collect { movieDetails ->
                    LOG.d("Get details about ${movieDetails.title}..")
                    getVideos(movieDetails.id)
                    getCast(movieDetails.id)
                    getSimilar(movieDetails.id)
                    getWatchProviders(movieDetails.id)
                    getCrew(movieDetails.id)
                    getReleaseDates(movieDetails.id)
                    getBackdrops(movieDetails.id)
                    movieDetails.belongsToCollection?.let { collection ->
                        LOG.d("${movieDetails.title} is part of a Collection, retrieve collection details..")
                        getCollectionDetails(collection.id)
                    }
                }
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Result.Loading)
            try {
                movieRepository.getDetails(movieId).collectLatest { movieDetails ->
                    LOG.d("Movie details: $movieDetails")
                    _selectedMovie.emit(movieDetails)
                }
            } catch (e: Exception) {
                _selectedMovie.emit(Result.Error(e))
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
            } catch (e: Exception) {
                _collectionParts.emit(Result.Error(e))
            }
        }
    }

    private fun getBackdrops(id: Int) {
        viewModelScope.launch {
            _backdrops.emit(Result.Loading)
            try {
                movieRepository.getBackdrop(id).collectLatest { backdrops ->
                    _backdrops.emit(backdrops)
                }
            } catch (e: Exception) {
                _backdrops.emit(Result.Error(e))
            }
        }
    }

    private fun getVideos(movieId: Int) {
        viewModelScope.launch {
            _videos.emit(Result.Loading)
            try {
                movieRepository.getVideos(movieId).collectLatest { videos ->
                    _videos.emit(videos)
                }
            } catch (e: Exception) {
                _videos.emit(Result.Error(e))
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
            } catch (e: Exception) {
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
            } catch (e: Exception) {
                _cast.emit(Result.Error(e))
            }
        }
    }

    private fun getWatchProviders(movieId: Int) {
        viewModelScope.launch {
            _watchProviders.emit(Result.Loading)
            try {
                movieRepository.getWatchProviders(movieId, Locale.getDefault().country)
                    .collectLatest { countryResult ->
                        _watchProviders.emit(countryResult)
                    }
            } catch (e: Exception) {
                _watchProviders.emit(Result.Error(e))
            }
        }
    }

    private fun getCrew(movieId: Int) {
        viewModelScope.launch {
            _crew.emit(Result.Loading)
            try {
                movieRepository.getCrew(movieId).collectLatest { crew ->
                    _crew.emit(crew)
                }
            } catch (e: Exception) {
                _crew.emit(Result.Error(e))
            }
        }
    }

    private fun getReleaseDates(movieId: Int) {
        viewModelScope.launch {
            _releaseDates.emit(Result.Loading)
            try {
                movieRepository.getReleaseDates(movieId).collectLatest {
                    if (it is Result.Error) {
                        val error = it.exception
                        _releaseDates.emit(Result.Error(error))
                    } else if (it.succeeded) {
                        val successResult = it as Result.Success
                        val currentCountry = Locale.getDefault().country
                        val releaseDatesInCurrentCountry =
                            successResult.data.findReleaseDateByCountry(currentCountry)

                        //Get and set latest certification
                        val latestCertification =
                            releaseDatesInCurrentCountry?.getLatestReleaseCertification()
                        _latestCertification.value = latestCertification
                        //Emit latest release dates in the current country
                        LOG.d("Release dates in $currentCountry : $releaseDatesInCurrentCountry")
                        _releaseDates.emit(
                            Result.Success(
                                releaseDatesInCurrentCountry ?: emptyList()
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                _releaseDates.emit(Result.Error(e))
            }
        }
    }

}
