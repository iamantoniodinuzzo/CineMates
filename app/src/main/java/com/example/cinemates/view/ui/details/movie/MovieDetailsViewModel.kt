package com.example.cinemates.view.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.model.Collection
import com.example.cinemates.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    val similarMovies: MutableLiveData<List<Movie>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getSimilarMovies(movie.id).asLiveData()
        }as MutableLiveData<List<Movie>>

    val recommendedMovies: MutableLiveData<List<Movie>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getRecommendedMovies(movie.id).asLiveData()
        }as MutableLiveData<List<Movie>>

    val videos: MutableLiveData<List<Video>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getVideos(movie.id).asLiveData()
        } as MutableLiveData<List<Video>>


    val posters: MutableLiveData<List<Image>>
        get() = Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getPosters(movie.id).asLiveData()
        }as MutableLiveData<List<Image>>

    val backdrops: MutableLiveData<List<Image>>
        get() = Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getBackdrops(movie.id).asLiveData()
        }as MutableLiveData<List<Image>>

    val cast: MutableLiveData<List<Cast>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getMovieCast(movie.id).asLiveData()
        }as MutableLiveData<List<Cast>>

    val crew: LiveData<List<Crew>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getMovieCrew(movie.id).asLiveData()
        }

    val moviesBelongsCollection: LiveData<Collection> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movie.belongs_to_collection?.id?.let { collectionId ->
                movieRepository.getCollection(collectionId).asLiveData()
            }
        }

    fun onDetailsFragmentReady(movie: Movie) =
        getMovieDetails(movie.id)


    fun onDestroyFragment() {
        videos.value = listOf()
        cast.value = listOf()
        backdrops.value = listOf()
        posters.value = listOf()
        similarMovies.value = listOf()
        recommendedMovies.value = listOf()
    }


    private fun getMovieDetails(movieId: Int) {
        movieRepository.getMovieDetails(movieId)
            .mapLatest { currentMovie ->
                _selectedMovie.postValue(currentMovie)
            }
            .launchIn(viewModelScope)
    }


}