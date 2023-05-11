package com.example.cinemates.ui.discover

import androidx.lifecycle.ViewModel
import com.example.cinemates.domain.model.common.MediaFilter
import com.example.cinemates.domain.usecases.discover.DiscoverUseCaseContainer
import com.example.cinemates.util.MediaSortOption
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.MovieSortOption
import com.example.cinemates.util.TvSortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoverUseCaseContainer: DiscoverUseCaseContainer,
) : ViewModel() {

    private val _mediaFilter = MutableStateFlow<MediaFilter>(
        MediaFilter.Builder().build()
    )
    val mediaFilter: StateFlow<MediaFilter> get() = _mediaFilter

    val sortByList = mediaFilter.flatMapLatest { filter ->
        when (filter.mediaType) {
            MediaType.MOVIE -> flowOf(
                listOf(
                    MediaSortOption.Popular,
                    MediaSortOption.ReleaseDate,
                    MovieSortOption.VoteAverage,
                    MovieSortOption.Revenue,
                    MovieSortOption.VoteCount
                )
            )

            MediaType.TV -> flowOf(
                listOf(
                    MediaSortOption.Popular,
                    MediaSortOption.ReleaseDate,
                    TvSortOption.FirstAirDate
                )
            )
        }
    }

    val genres = mediaFilter.flatMapLatest { filter ->
        when (filter.mediaType) {
            MediaType.MOVIE -> discoverUseCaseContainer.getMovieGenresUseCase.invoke()
            MediaType.TV -> discoverUseCaseContainer.getTvGenresUseCase.invoke()
        }
    }

    val discoverableMedia = mediaFilter.flatMapLatest { filter ->
        when (filter.mediaType) {
            MediaType.MOVIE -> discoverUseCaseContainer.getMovieByDiscover.invoke(filter)
            MediaType.TV -> discoverUseCaseContainer.getTvByDiscover.invoke(filter)
        }
    }

    fun updateMediaFilter(mediaFilterBuilder: MediaFilter.Builder) {
        _mediaFilter.value = mediaFilterBuilder.build()
    }

}