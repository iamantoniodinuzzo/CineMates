package com.example.cinemates.view.ui.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.CreditsResponse
import com.example.cinemates.model.data.Crew
import com.example.cinemates.model.data.ImagesResponse
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.PersonalStatus
import com.example.cinemates.model.data.Video
import com.example.cinemates.model.data.setPersonalStatus
import com.example.cinemates.model.repository.MovieRepository
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
private const val TAG = "MovieDetailsViewModel"

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

    val videos: LiveData<List<Video>> =
        Transformations.switchMap(_selectedMovie) { movie ->
            movieRepository.getVideos(movie.id).asLiveData()
        }

    val imagesResponse: LiveData<ImagesResponse> =
        Transformations.switchMap(_selectedMovie) { movie ->
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


    fun setFavorite() =
        selectedMovie.value?.setFavorite()


    fun setPersonalStatus(status: PersonalStatus) =
        selectedMovie.value?.setPersonalStatus(status)


    private fun getMovieDetails(movieId: Int) {
        movieRepository.getMovieDetails(movieId)
            .mapLatest { currentMovie ->
                _selectedMovie.postValue(currentMovie)
            }
            .launchIn(viewModelScope)
    }


}