package com.example.cinemates.view.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.model.entities.*
import com.example.cinemates.model.repository.MovieRepository
import com.example.cinemates.network.response.CreditsResponse
import com.example.cinemates.network.response.ImagesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment] [CollectionDialogFragment]
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

    val similarMovies: LiveData<List<Movie>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getSimilarMovies(movie.id).asLiveData()
        }

    val videos: MutableLiveData<List<Video>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getVideos(movie.id).asLiveData()
        } as MutableLiveData<List<Video>>


    val imagesResponse: LiveData<ImagesResponse>
        get() = Transformations.switchMap(_selectedMovie) { movie ->
                movieRepository.getImages(movie.id).asLiveData()
            }

    private val credits: LiveData<CreditsResponse> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getMovieCredits(movie.id).asLiveData()
        }

    val cast: LiveData<List<Cast>> =
        Transformations.map(credits, CreditsResponse::cast)

    val crew: LiveData<List<Crew>> =
        Transformations.map(credits, CreditsResponse::crew)

    val moviesBelongsCollection: LiveData<List<Movie>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movie.belongs_to_collection?.id?.let { collectionId ->
                movieRepository.getCollection(collectionId).asLiveData()
            }
        }

    fun onDetailsFragmentReady(movie: Movie) =
        getMovieDetails(movie.id)

    fun onDestroyView() {
        videos.value = listOf()
    }

    private fun getMovieDetails(movieId: Int) {
        movieRepository.getMovieDetails(movieId)
            .mapLatest { currentMovie ->
                _selectedMovie.postValue(currentMovie)
            }
            .launchIn(viewModelScope)
    }


}