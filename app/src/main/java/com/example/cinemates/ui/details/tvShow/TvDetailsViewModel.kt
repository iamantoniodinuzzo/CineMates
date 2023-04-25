package com.example.cinemates.ui.details.tvShow

import androidx.lifecycle.*
import com.example.cinemates.domain.model.EpisodeGroupDetails
import com.example.cinemates.domain.model.SeasonDetails
import com.example.cinemates.domain.model.TvShow
import com.example.cinemates.domain.usecases.details.tv.GetSeasonDetailsUseCase
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
    private val getTvShowDetailsUseCase: GetTvDetailsUseCase,
    private val getSeasonDetailsUseCase: GetSeasonDetailsUseCase,
) : ViewModel() {

    private val _selectedTv = MutableStateFlow<TvShow?>(null)
    val selectedTv: Flow<TvShow?> get() = _selectedTv
    private val _episodeGroupDetail = MutableStateFlow<EpisodeGroupDetails?>(null)
    val episodeGroupDetail: Flow<EpisodeGroupDetails?> get() = _episodeGroupDetail
    private val _seasonDetails = MutableStateFlow<SeasonDetails?>(null)
    val seasonDetails: Flow<SeasonDetails?> get() = _seasonDetails
    private var selectedTvId: Int = -1

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
                selectedTvId = tv.id
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

    fun getSeasonDetails(seasonNumber: Int) {
        viewModelScope.launch {
            getSeasonDetailsUseCase.invoke(selectedTvId, seasonNumber).collectLatest {
                _seasonDetails.value = it
            }
        }
    }


}