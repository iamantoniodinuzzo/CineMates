package com.indisparte.cinemates.ui.discover

import androidx.lifecycle.ViewModel
import com.indisparte.cinemates.domain.model.common.MediaFilter
import com.indisparte.cinemates.util.MediaSortOption
import com.indisparte.cinemates.util.MediaType
import com.indisparte.cinemates.util.MovieSortOption
import com.indisparte.cinemates.util.TvSortOption
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


    fun updateMediaFilter(mediaFilterBuilder: MediaFilter.Builder) {
        _mediaFilter.value = mediaFilterBuilder.build()
    }

}