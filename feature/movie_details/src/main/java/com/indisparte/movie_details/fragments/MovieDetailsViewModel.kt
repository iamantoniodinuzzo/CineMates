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
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import com.indisparte.network.whenResources
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
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

    /*Cast*/
    private val _cast = MutableStateFlow<Result<List<Cast>>>(Result.Success(emptyList()))
    val cast: StateFlow<Result<List<Cast>>> get() = _cast

    /*Similar movies*/
    private val _similarMovies = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val similarMovies: StateFlow<Result<List<Movie>>> get() = _similarMovies

    /*Collection parts*/
    private val _collectionParts = MutableStateFlow<Result<CollectionDetails>?>(null)
    val collectionParts: StateFlow<Result<CollectionDetails>?> get() = _collectionParts

    private val _movieInfo = MutableStateFlow<Result<MovieInfoUiState>?>(null)
    val movieInfo: StateFlow<Result<MovieInfoUiState>?> get() = _movieInfo

    init {
        observeSelectedMovie()
    }

    fun onDetailsFragmentReady(id: Int) {
        getMovieDetails(id)
        getCast(id)
        getSimilar(id)
    }


    fun updateGenre(genre: Genre) {
        LOG.d("Update $genre")
        genreRepository.updateSavedGenre(genre)

    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Result.Loading)
            LOG.d("Loading state movie details...")
            try {
                movieRepository.getDetails(id).collectLatest { movieDetails ->
                    LOG.d("Emit movie details result: $movieDetails")
                    _selectedMovie.emit(movieDetails)
                }
            } catch (e: CineMatesExceptions) {
                LOG.e("An error in get movie details: $e")
                _selectedMovie.emit(Result.Error(e))
            }
        }
    }

    private fun observeSelectedMovie() {
        viewModelScope.launch {
            _selectedMovie.distinctUntilChanged().collect { result ->
                result.whenResources(
                    onSuccess = { movieDetails ->
                        LOG.d("Get details about ${movieDetails.title}..")
                        loadMovieInfo(movieDetails)
                        movieDetails.belongsToCollection?.let { collection ->
                            LOG.d("${movieDetails.title} is part of a Collection, retrieve collection details..")
                            getCollectionDetails(collection.id)
                        }
                    },
                    onError = { error ->
                        LOG.e("Error in selected movie: $error")
                        _movieInfo.emit(Result.Error(error))
                    },
                    onLoading = {
                        _movieInfo.emit(Result.Loading)

                    }
                )


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
                    movieRepository.getDetails(movieId)
                        .flowOn(Dispatchers.IO),
                    movieRepository.getVideos(movieId)
                        .flowOn(Dispatchers.IO),
                    movieRepository.getWatchProviders(movieId, country)
                        .flowOn(Dispatchers.IO),
                    movieRepository.getCrew(movieId)
                        .flowOn(Dispatchers.IO),
                    movieRepository.getReleaseDates(movieId)
                        .flowOn(Dispatchers.IO),
                    movieRepository.getBackdrop(movieId)
                        .flowOn(Dispatchers.IO),
                ) { (details, videos, watchProviders, crew, releaseDates, backdrops) ->
                    listOf(details, videos, watchProviders, crew, releaseDates, backdrops)
                }.filter { result -> result.all { it is Result.Success } }
                    .collectLatest { results ->
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

                        //get updated genres from local database to check which genre is favorite
                        val genresId = movieDetails.genres.map { it.id }
                        val updatedGenresFlow = genreRepository.getGenresByIds(genresId)
                        //update current movie details genres
                        movieDetails.genres = updatedGenresFlow.first()

                        val movieInfoUiState = MovieInfoUiState(
                            movieDetails,
                            videosResult,
                            watchProvidersResult,
                            releaseDates,
                            latestCertification,
                            backdropsResult,
                            crewResult
                        )
                        LOG.d("Emit Movie Info UI State")
                        _movieInfo.emit(Result.Success(movieInfoUiState))
                    }

            } catch (e: Exception) {
                LOG.e("Error in loadMovieInfo: ${e.localizedMessage}")
                _movieInfo.emit(
                    Result.Error(CineMatesExceptions.GenericException)
                )
            }
        }

    }


    /* private suspend fun populateMovieInfo() = coroutineScope {
         try {
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
                 LOG.d("Emit successful movie info")
                 _movieInfo.emit(Result.Success(movieInfo))
             } else {
                 // Gestisci un errore in caso di fallimento nella ricezione dei dati
                 val error = _selectedMovie.filterIsInstance<Result.Error>()
                     .firstOrNull() ?: _videos.filterIsInstance<Result.Error>()
                     .firstOrNull() ?: _watchProviders.filterIsInstance<Result.Error>()
                     .firstOrNull() ?: _releaseDates.filterIsInstance<Result.Error>()
                     .firstOrNull() ?: _backdrops.filterIsInstance<Result.Error>()
                     .firstOrNull()
                 LOG.d("Emit error in  movie info")
                 _movieInfo.emit(
                     Result.Error(
                         error?.exception ?: CineMatesExceptions.GenericException
                     )
                 )
             }
         } catch (e: Exception) {
             LOG.e("Error populating movie info: $e")
             _movieInfo.emit(Result.Error(CineMatesExceptions.GenericException))
         }
     }*/


    private fun getCollectionDetails(collectionId: Int) {
        viewModelScope.launch {
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

    }

    private fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            _similarMovies.emit(Result.Loading)
            try {
                movieRepository.getSimilar(movieId).collectLatest { similar ->

                    _similarMovies.emit(similar)
                }
            } catch (e: CineMatesExceptions) {

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
            } catch (e: CineMatesExceptions) {
                _cast.emit(Result.Error(e))
            }
        }
    }


}

private operator fun <T> Array<T>.component6(): T = get(5)
