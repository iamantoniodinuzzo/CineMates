package com.indisparte.movie_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.common.Backdrop
import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.movie_data.CollectionDetails
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.MovieDetails
import com.indisparte.movie_data.ReleaseDate
import com.indisparte.movie_data.findReleaseDateByCountry
import com.indisparte.movie_data.getLatestReleaseCertification
import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.movie_details.model.MovieInfoUiState
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.network.succeeded
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.firstOrNull
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

    private val LOG = Timber.tag(MovieDetailsViewModel::class.java.simpleName)

    private val _selectedMovie = MutableSharedFlow<Result<MovieDetails>>()

    /*Videos*/
    private val _videos = MutableStateFlow<Result<List<Video>>>(Result.Success(emptyList()))

    /*Cast*/
    private val _cast = MutableStateFlow<Result<List<Cast>>>(Result.Success(emptyList()))
    val cast: StateFlow<Result<List<Cast>>> get() = _cast

    /*Similar movies*/
    private val _similarMovies = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val similarMovies: StateFlow<Result<List<Movie>>> get() = _similarMovies

    /*Watch providers*/
    private val _watchProviders = MutableStateFlow<Result<CountryResult?>>(Result.Success(null))

    /*Crew*/
    private val _crew = MutableStateFlow<Result<List<Crew>>>(Result.Success(emptyList()))

    /*Release Dates*/
    private val _releaseDates =
        MutableStateFlow<Result<List<ReleaseDate>>>(Result.Success(emptyList()))

    /*Latest certification*/
    private val _latestCertification = MutableStateFlow<String?>(null)

    /*Backdrops*/
    private val _backdrops = MutableStateFlow<Result<List<Backdrop>>>(Result.Success(emptyList()))

    /*Collection parts*/
    private val _collectionParts = MutableStateFlow<Result<CollectionDetails>?>(null)
    val collectionParts: StateFlow<Result<CollectionDetails>?> get() = _collectionParts

    private val _movieInfo = MutableStateFlow<Result<MovieInfoUiState>?>(null)
    val movieInfo: StateFlow<Result<MovieInfoUiState>?> get() = _movieInfo

    init {
        observeSelectedMovie()
    }

    fun onDetailsFragmentReady(id: Int) = getMovieDetails(id)

    private fun observeSelectedMovie() {
        viewModelScope.launch {
            _selectedMovie.collect { result ->
                when (result) {
                    is Result.Success -> {
                        val movieDetails = result.data
                        LOG.d("Get details about ${movieDetails.title}..")
                        val videosDeferred = async { getVideos(movieDetails.id) }
                        val castDeferred = async { getCast(movieDetails.id) }
                        val similarMoviesDeferred = async { getSimilar(movieDetails.id) }
                        val watchProvidersDeferred = async { getWatchProviders(movieDetails.id) }
                        val crewDeferred = async { getCrew(movieDetails.id) }
                        val releaseDatesDeferred = async { getReleaseDates(movieDetails.id) }
                        val backdropsDeferred = async { getBackdrops(movieDetails.id) }
                        movieDetails.belongsToCollection?.let { collection ->
                            LOG.d("${movieDetails.title} is part of a Collection, retrieve collection details..")
                            val collectionDetailsDeferred =
                                async { getCollectionDetails(collection.id) }
                            collectionDetailsDeferred.await()
                        }

                        awaitAll(
                            videosDeferred,
                            castDeferred,
                            similarMoviesDeferred,
                            watchProvidersDeferred,
                            crewDeferred,
                            releaseDatesDeferred,
                            backdropsDeferred
                        )

                    }

                    is Result.Error -> {
                        val error = result.exception
                        LOG.e("Error in selected movie: $error")
                        _movieInfo.emit(Result.Error(error))
                    }

                    is Result.Loading -> {
                        _movieInfo.emit(Result.Loading)
                    }

                    else -> {
                        // Gestisci altri casi, se necessario
                    }
                }

            }
        }
        viewModelScope.launch {
            populateMovieInfo()

        }

    }


    private suspend fun populateMovieInfo() {

        val movieDetailsResult =
            _selectedMovie.filterIsInstance<Result.Success<MovieDetails>>().firstOrNull()
        val videosResult = _videos.filterIsInstance<Result.Success<List<Video>>>().firstOrNull()
        val watchProviderResult =
            _watchProviders.filterIsInstance<Result.Success<CountryResult?>>().firstOrNull()
        val releaseDatesResult =
            _releaseDates.filterIsInstance<Result.Success<List<ReleaseDate>>>().firstOrNull()
        val backdropsResult =
            _backdrops.filterIsInstance<Result.Success<List<Backdrop>>>().firstOrNull()
        val crew =
            _crew.filterIsInstance<Result.Success<List<Crew>>>().firstOrNull()

        if (movieDetailsResult != null && videosResult != null && watchProviderResult != null
            && releaseDatesResult != null && backdropsResult != null && crew != null
        ) {

            // Tutti i dati sono stati ottenuti con successo
            val movieInfo = MovieInfoUiState(
                movieDetails = movieDetailsResult.data,
                videos = videosResult.data,
                watchProvider = watchProviderResult.data,
                releaseDates = releaseDatesResult.data,
                latestCertification = _latestCertification.value,
                backdrops = backdropsResult.data,
                crew = crew.data
            )

            _movieInfo.emit(Result.Success(movieInfo))
        } else {
            // Gestisci un errore in caso di fallimento nella ricezione dei dati
            val error = _selectedMovie.filterIsInstance<Result.Error>()
                .firstOrNull() ?: _videos.filterIsInstance<Result.Error>()
                .firstOrNull() ?: _watchProviders.filterIsInstance<Result.Error>()
                .firstOrNull() ?: _releaseDates.filterIsInstance<Result.Error>()
                .firstOrNull() ?: _backdrops.filterIsInstance<Result.Error>()
                .firstOrNull()

            _movieInfo.emit(
                Result.Error(
                    error?.exception ?: CineMatesExceptions.GenericException
                )
            )
        }

    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Result.Loading)
            LOG.d("Loading state movie details...")
            try {
                movieRepository.getDetails(movieId).collectLatest { movieDetails ->
                    LOG.d("Emit success with movie details: ${movieDetails}")
                    _selectedMovie.emit(movieDetails)
                }
            } catch (e: CineMatesExceptions) {
                LOG.e("An error in movie details: $e")
                _selectedMovie.emit(Result.Error(e))
            }
        }

    }

    private suspend fun getCollectionDetails(collectionId: Int) {
        _collectionParts.emit(Result.Loading)
        try {
            movieRepository.getCollectionDetails(collectionId)
                .collectLatest { collectionDetails ->

                    _collectionParts.emit(collectionDetails)
                }
        } catch (e: CineMatesExceptions) {

            _collectionParts.emit(Result.Error(e))
        }

    }

    private suspend fun getBackdrops(id: Int) {
        _backdrops.emit(Result.Loading)
        try {
            movieRepository.getBackdrop(id).collectLatest { backdrops ->
                _backdrops.emit(backdrops)
            }
        } catch (e: CineMatesExceptions) {
            _backdrops.emit(Result.Error(e))
        }

    }

    private suspend fun getVideos(movieId: Int) {
        _videos.emit(Result.Loading)
        try {
            movieRepository.getVideos(movieId).collectLatest { videos ->

                _videos.emit(videos)
            }
        } catch (e: CineMatesExceptions) {

            _videos.emit(Result.Error(e))
        }

    }

    private suspend fun getSimilar(movieId: Int) {

        _similarMovies.emit(Result.Loading)
        try {
            movieRepository.getSimilar(movieId).collectLatest { similar ->

                _similarMovies.emit(similar)
            }
        } catch (e: CineMatesExceptions) {

            _similarMovies.emit(Result.Error(e))
        }

    }

    private suspend fun getCast(movieId: Int) {

        _cast.emit(Result.Loading)
        try {
            movieRepository.getCast(movieId).collectLatest { cast ->
                _cast.emit(cast)

            }
        } catch (e: CineMatesExceptions) {
            _cast.emit(Result.Error(e))
        }
    }


    private suspend fun getWatchProviders(movieId: Int) {

        _watchProviders.emit(Result.Loading)
        try {
            movieRepository.getWatchProviders(movieId, Locale.getDefault().country)
                .collectLatest { countryResult ->
                    _watchProviders.emit(countryResult)
                }
        } catch (e: CineMatesExceptions) {
            _watchProviders.emit(Result.Error(e))
        }
    }


    private suspend fun getCrew(movieId: Int) {

        _crew.emit(Result.Loading)
        try {
            movieRepository.getCrew(movieId).collectLatest { crew ->
                _crew.emit(crew)
            }
        } catch (e: CineMatesExceptions) {
            _crew.emit(Result.Error(e))
        }


    }

    private suspend fun getReleaseDates(movieId: Int) {

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
        } catch (e: CineMatesExceptions) {
            _releaseDates.emit(Result.Error(e))
        }
    }

}
