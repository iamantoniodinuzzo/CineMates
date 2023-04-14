package com.example.cinemates.ui.details.tvShow

import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.data.remote.repository.TvShowRepositoryImpl
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
    private val tvShowRepositoryImpl: TvShowRepositoryImpl
) : ViewModel() {

    private val _selectedTv = MutableStateFlow<TvShow?>(null)
    private val _episodeGroupDetail = MutableStateFlow<EpisodeGroup?>(null)
    val episodeGroupDetail: Flow<EpisodeGroup?> get() = _episodeGroupDetail
    val selectedTv: Flow<TvShow?> get() = _selectedTv

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
            tvShowRepositoryImpl.getDetails(id)
                .collectLatest { tv ->
                    _selectedTv.value = tv
                }
        }

    }

    val similarTvShow = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getSimilar(it.id)
        } ?: emptyFlow()
    }

    val videos = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getVideos(it.id)
        } ?: emptyFlow()
    }

    val posters = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getPosters(it.id)
        } ?: emptyFlow()
    }

    val backdrops = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getBackdrops(it.id)
        } ?: emptyFlow()
    }

    val cast = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getCrew(it.id)
        } ?: emptyFlow()
    }

    val episodeGroupList = selectedTv.flatMapLatest { tv ->
        tv?.let {
            tvShowRepositoryImpl.getEpisodeGroup(tv.id)
        } ?: emptyFlow()
    }


    fun getEpisodeGroupDetails(id: String) {
        viewModelScope.launch {
            tvShowRepositoryImpl.getEpisodeGroupDetails(id).collectLatest {
                _episodeGroupDetail.value = it
            }
        }
    }


}