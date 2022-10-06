package com.example.cinemates.util

/**
 * @author Antonio Di Nuzzo
 * Created 23/07/2022 at 10:51
 */
enum class Sort(val attribute: String) {
    POPULARITY("popularity.desc"), RELEASE_DATE("release_date.desc"), VOTE_AVERAGE("vote_average.desc");

    override fun toString(): String {
        return when (this) {
            POPULARITY -> {
                "Most Popular"
            }
            RELEASE_DATE -> {
                "Next Movies"
            }
            VOTE_AVERAGE -> {
                "Most voted"
            }
        }
        throw AssertionError("Unknown $this")
    }
}