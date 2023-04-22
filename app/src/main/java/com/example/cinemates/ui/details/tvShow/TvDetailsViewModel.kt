package com.example.cinemates.ui.details.tvShow

import android.util.Log
import androidx.lifecycle.*
import com.example.cinemates.domain.model.EpisodeGroupDetails
import com.example.cinemates.domain.model.TvShow
import com.example.cinemates.domain.usecases.details.tv.GetTvDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */
private val TAG = TvDetailsViewModel::class.simpleName

@HiltViewModel
class TvDetailsViewModel
@Inject
constructor(
    private val getTvShowDetailsUseCase: GetTvDetailsUseCase
) : ViewModel() {

    private val _selectedTv = MutableStateFlow<TvShow?>(null)
    val selectedTv: Flow<TvShow?> get() = _selectedTv
    private val _episodeGroupDetail = MutableStateFlow<EpisodeGroupDetails?>(null)
    val episodeGroupDetail: Flow<EpisodeGroupDetails?> get() = _episodeGroupDetail

    /**
     * Retrieves additional information about the selected tvShow
     */
    fun onDetailsFragmentReady(id: Int) =
        getTvDetails(id)

    fun onFragmentDestroyed() = viewModelScope.launch { _episodeGroupDetail.value = null }

    /*
        Through the show id , it retrieves the details.
     */
    private fun getTvDetails(id: Int) {
        viewModelScope.launch {
            getTvShowDetailsUseCase.getTvDetails(id).collectLatest { tv ->
                    _selectedTv.value = tv
                }
        }

    }

    val similarTvShow = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getSimilarTvs(it.id)
        } ?: emptyFlow()
    }

    val videos = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getTrailers(it.id)
        } ?: emptyFlow()
    }

    val posters = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getPosters(it.id)
        } ?: emptyFlow()
    }

    val backdrops = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getBackdrops(it.id)
        } ?: emptyFlow()
    }

    val cast = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getTvCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getTvCrew(it.id)
        } ?: emptyFlow()
    }

    val episodeGroupList = selectedTv.flatMapLatest { tv ->
        tv?.let {
            getTvShowDetailsUseCase.getEpisodeGroups(tv.id)
        } ?: emptyFlow()
    }


    fun getEpisodeGroupDetails(episodeGroupId: String) {
        viewModelScope.launch {
            getTvShowDetailsUseCase.getEpisodeGroupDetails(episodeGroupId).collectLatest {
                _episodeGroupDetail.value = it
            }
        }
    }


}