package com.indisparte.home.util

import androidx.annotation.StringRes
import com.indisparte.base.Media
import com.indisparte.movie_data.Movie
import com.indisparte.network.util.Result
import com.indisparte.base.Person
import com.indisparte.base.TMDBItem
import com.indisparte.tv.TvShow


/**
 * Sealed class representing a section.
 *
 * @param titleResId The string resource ID for the section title.
 */
sealed class Section(@StringRes val titleResId: Int, val result: Result<List<Media>>) {
    /**
     * Subclass representing a movie section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param result The resource representing the list of movies.
     */
    class MovieSection(
        @StringRes titleResId: Int,
         result: Result<List<Movie>>,
    ) :
        Section(titleResId, result) {
        override fun toString(): String {
            return "MovieSection(moviesResult=$result)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MovieSection) return false

            if (result != other.result) return false

            return true
        }

        override fun hashCode(): Int {
            return result.hashCode()
        }


    }

    /**
     * Subclass representing a TV show section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param tvShowsResult The resource representing the list of TV shows.
     */
    class TvShowSection(
        @StringRes titleResId: Int,
        val tvShowsResult: Result<List<TvShow>>,
    ) :
        Section(titleResId, tvShowsResult) {
        override fun toString(): String {
            return "TvShowSection(tvShowsResult=$tvShowsResult)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is TvShowSection) return false

            if (tvShowsResult != other.tvShowsResult) return false

            return true
        }

        override fun hashCode(): Int {
            return tvShowsResult.hashCode()
        }


    }

    /**
     * Subclass representing a people section.
     *
     * @param titleResId The string resource ID for the section title.
     * @param peopleResult The resource representing the list of people.
     */
//    class PeopleSection(
//        @StringRes titleResId: Int,
//        val peopleResult: Result<List<Person>>,
//    ) :
//        Section(titleResId, peopleResult) {
//        override fun toString(): String {
//            return "PeopleSection(peopleResult=$peopleResult)"
//        }
//
//        override fun equals(other: Any?): Boolean {
//            if (this === other) return true
//            if (other !is PeopleSection) return false
//
//            if (peopleResult != other.peopleResult) return false
//
//            return true
//        }
//
//        override fun hashCode(): Int {
//            return peopleResult.hashCode()
//        }
//
//
//    }


}
