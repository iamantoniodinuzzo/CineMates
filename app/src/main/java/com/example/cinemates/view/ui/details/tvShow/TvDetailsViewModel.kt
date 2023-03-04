package com.example.cinemates.view.ui.details.tvShow

import android.provider.MediaStore.Audio.Genres
import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */

@HiltViewModel
class TvDetailsViewModel
@Inject
constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val TAG = TvDetailsViewModel::class.simpleName

    //It was decided to use a MutableSharedFlow rather than a MutableStateFlow
    //because the latter involves an initial value that must be set.
    private val _selectedTv = MutableSharedFlow<TvShow>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val selectedTv: Flow<TvShow> = _selectedTv.distinctUntilChanged()

//    val partsOfCollection: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())


    /**
     * Retrieves additional information about the selected movie
     */
    fun onDetailsFragmentReady(id: Int) =
        getTvDetails(id)

    /*
        Through the film id , it retrieves the details and checks if the film is part of a collection.
        If it is successful, it initializes the variable containing the parts of the collection
     */
    private fun getTvDetails(id: Int) {
        tvShowRepository.getTvShowDetails(id)
            .mapLatest { tv ->
                _selectedTv.tryEmit(tv)
//                checkIfMovieIsAPartOfACollection(movie.belongs_to_collection)
            }
            .launchIn(viewModelScope)
    }

    /* private fun checkIfMovieIsAPartOfACollection(
         belongs_to_collection: Collection?
     ) {
         if (belongs_to_collection != null)
             getMoviesBelongCollection(belongs_to_collection.id)
     }
 */
    val similarTvShow = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getSimilarTvShow(it.tvShow.id)
    }

    /*val recommendedMovies = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getRecommendedMovies(it.tvShow.id)
    }*/

    val videos = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getVideos(it.tvShow.id)
    }

    val posters = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getPosters(it.tvShow.id)
    }

    val backdrops = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getBackdrops(it.tvShow.id)
    }

    val cast = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getTvShowCast(it.tvShow.id)
    }

    val crew = combine(
        selectedTv
    ) { (query) ->
        SelectedTv(query)
    }.flatMapLatest {
        tvShowRepository.getTvShowCrew(it.tvShow.id)
    }

//Todo get parts
    /* private fun getMoviesBelongCollection(collectionId: Int) = viewModelScope.launch {
         tvShowRepository.getCollection(collectionId).collect {
             partsOfCollection.value = it.parts
         }
     }*/

}

private data class SelectedTv(val tvShow: TvShow)