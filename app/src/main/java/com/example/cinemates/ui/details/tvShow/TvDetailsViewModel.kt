package com.example.cinemates.ui.details.tvShow

import androidx.lifecycle.*
import com.example.cinemates.domain.model.tv.EpisodeGroupDetails
import com.example.cinemates.domain.model.tv.SeasonDetails
import com.example.cinemates.domain.model.tv.TvShow
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


    }


}