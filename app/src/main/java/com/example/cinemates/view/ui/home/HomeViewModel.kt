package com.example.cinemates.view.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.repository.MovieRepository
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is attached to the [HomeFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<Movie>>()
    val trendingMovies: LiveData<List<Movie>> get() = _trendingMovies

    private val _trendingPerson = MutableLiveData<List<Person>>()
    val trendingPerson: LiveData<List<Person>> get() = _trendingPerson

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>> get() = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>> get() = _upcomingMovies

    init {
        getTrendingMovies()
        getTrendingPerson()
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getTrendingMovies() = viewModelScope.launch {
        movieRepository.getTrendingMovies(MediaType.MOVIE.toString(), TimeWindow.WEEK.toString())
            .let { response ->

                  if(response.isSuccessful){
                      _trendingMovies.postValue(response.body()?.results)
                  }else{
                      Log.d(TAG, "getTrendingMovies Error: ${response.code()}")
                  }
            }
    }

    private fun getTrendingPerson() = viewModelScope.launch {
        movieRepository.getTrendingPerson(MediaType.PERSON.toString(), TimeWindow.WEEK.toString())
            .let { response ->

                  if(response.isSuccessful){
                      _trendingPerson.postValue(response.body()?.results)
                  }else{
                      Log.d(TAG, "getTrendingPerson Error: ${response.code()}")
                  }
            }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        movieRepository.getPopularMovies().let { response ->

                  if(response.isSuccessful){
                      _popularMovies.postValue(response.body()?.results)
                  }else{
                      Log.d(TAG, "getPopularMovies Error: ${response.code()}")
                  }
            }
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        movieRepository.getTopRatedMovies().let { response ->

                  if(response.isSuccessful){
                      _topRatedMovies.postValue(response.body()?.results)
                  }else{
                      Log.d(TAG, "getTopRatedMovies Error: ${response.code()}")
                  }
            }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        movieRepository.getUpcomingMovies().let { response ->

                  if(response.isSuccessful){
                      _upcomingMovies.postValue(response.body()?.results)
                  }else{
                      Log.d(TAG, "getUpcomingMovies Error: ${response.code()}")
                  }
            }
    }

}

