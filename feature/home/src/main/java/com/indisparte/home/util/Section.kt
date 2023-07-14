package com.indisparte.home.util

import androidx.annotation.StringRes
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.Person
import com.indisparte.model.entity.TvShow
import com.indisparte.network.Resource


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class Section(@StringRes val titleResId: Int) {
    class MovieSection(@StringRes titleResId: Int, val movies: Resource<List<Movie>>?) : Section(titleResId)
    class TvShowSection(@StringRes titleResId: Int, val tvShows: Resource<List<TvShow>>?) : Section(titleResId)
    class PeopleSection(@StringRes titleResId: Int, val people: Resource<List<Person>>?) : Section(titleResId)
}
