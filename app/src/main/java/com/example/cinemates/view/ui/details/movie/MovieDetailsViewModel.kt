package com.example.cinemates.view.ui.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Collection
import com.example.cinemates.model.data.Images
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Video
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment] [CollectionDialogFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
private const val TAG = "MovieDetailsViewModel"

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> get() = _similarMovies

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _images = MutableLiveData<Images>()
    val images: LiveData<Images> get() = _images

    private val _cast = MutableLiveData<List<Cast>>()
    val cast: LiveData<List<Cast>> get() = _cast

    private val _moviesBelongsCollection = MutableLiveData<Collection>()
    val moviesBelongsCollection: LiveData<Collection> get() = _moviesBelongsCollection

    fun setSelectedMovie(movie: Movie) {
        getMovieDetails(movie.id)
        getSimilarMovies(movie.id)
        getMovieVideos(movie.id)
        getMovieImages(movie.id)
        getMovieCast(movie.id)
        getMoviesBelongsCollection(movie.belongs_to_collection.id)
    }

    private fun getMoviesBelongsCollection(collectionId: Int) {
        movieRepository.getCollection(collectionId)
            .let { response ->
                //TODO switch RxJava to Coroutines

                /*  if(response.isSuccessful){
                val collection : Collection = response.body()
                        _moviesBelongsCollection.value = collection.getParts()
                  }else{
                      Log.d(TAG, "getMoviesBelongsCollection Error: ${response.code()}")
                  }*/
            }
    }

    private fun getMovieCast(movieId: Int) {
        movieRepository.getMovieDetails(movieId)
            .let { response ->
                //TODO switch RxJava to Coroutines

                /*  if(response.isSuccessful){
                        _cast.value = response.body()
                  }else{
                      Log.d(TAG, "getMovieCast Error: ${response.code()}")
                  }*/
            }
    }

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        movieRepository.getMovieDetails(movieId)
            .let { response ->
                //TODO switch RxJava to Coroutines

                /*  if(response.isSuccessful){
                        _selectedMovie.value = response.body()
                  }else{
                      Log.d(TAG, "getMovieDetails Error: ${response.code()}")
                  }*/
            }

    }

    private fun getMovieImages(movieId: Int) {
        movieRepository.getImages(movieId).let { response ->
            //TODO switch RxJava to Coroutines

            /*  if(response.isSuccessful){
                    _images.value = response.body()
              }else{
                  Log.d(TAG, "getMovieImages Error: ${response.code()}")
              }*/
        }
    }

    private fun getMovieVideos(movieId: Int) {
        movieRepository.getVideos(movieId).let { response ->
            //TODO switch RxJava to Coroutines

            /*  if(response.isSuccessful){
                    _videos.value = response.body()
              }else{
                  Log.d(TAG, "getMovieVideos Error: ${response.code()}")
              }*/
        }
    }

    private fun getSimilarMovies(movieId: Int) = viewModelScope.launch {
        movieRepository.getSimilar(movieId).let { response ->
            //TODO switch RxJava to Coroutines

            /*  if(response.isSuccessful){
                    _similarMovies.value = response.body()
              }else{
                  Log.d(TAG, "getSimilarMovies Error: ${response.code()}")
              }*/
        }
    }

}