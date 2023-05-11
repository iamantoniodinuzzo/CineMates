package com.example.cinemates.ui.discover

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cinemates.domain.model.common.MediaFilter
import com.example.cinemates.domain.usecases.discover.DiscoverUseCaseContainer
import com.example.cinemates.util.MediaSortOption
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.MovieSortOption
import com.example.cinemates.util.TvSortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private val TAG = DiscoverViewModel::class.simpleName
/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DiscoverViewModel
@Inject
constructor(
    private val discoverUseCaseContainer: DiscoverUseCaseContainer,
) : ViewModel() {

    private val _mediaFilterBuilder =
        MutableStateFlow<MediaFilter>(MediaFilter.Builder(MediaType.MOVIE).build())
    private val mediaFilterBuilder: StateFlow<MediaFilter> get() = _mediaFilterBuilder

    val sortByList = mediaFilterBuilder.flatMapLatest {
        when (it.mediaType) {
            MediaType.MOVIE -> flow {
                emit(
                    listOf(
                        MediaSortOption.Popular,
                        MediaSortOption.ReleaseDate,
                        MovieSortOption.VoteAverage,
                        MovieSortOption.Revenue,
                        MovieSortOption.VoteCount
                    )
                )
            }
            MediaType.TV -> flow {
                emit(
                    listOf(
                        MediaSortOption.Popular,
                        MediaSortOption.ReleaseDate,
                        TvSortOption.FirstAirDate
                    )
                )
            }
        }
    }


    val genres =
        mediaFilterBuilder.flatMapLatest {

            when (it.mediaType) {
                MediaType.TV -> discoverUseCaseContainer.getTvGenresUseCase.invoke()
                MediaType.MOVIE -> discoverUseCaseContainer.getMovieGenresUseCase.invoke()
            }
        }

    val discoverableMedia =
        mediaFilterBuilder.flatMapLatest {
            Log.d(TAG, "Media triggered with filter: $it ")
            when (it.mediaType) {
                MediaType.MOVIE -> discoverUseCaseContainer.getMovieByDiscover.invoke(it)
                MediaType.TV -> discoverUseCaseContainer.getTvByDiscover.invoke(it)
            }
        }

    fun updateMediaFilterBuilder(mediaFilterBuilder: MediaFilter.Builder) {
        _mediaFilterBuilder.value = mediaFilterBuilder.build()
    }

}