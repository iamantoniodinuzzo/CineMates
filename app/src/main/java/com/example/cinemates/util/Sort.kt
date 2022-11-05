package com.example.cinemates.util

/**
 * Sorting options, default order is Descendant (desc)
 * @author Antonio Di Nuzzo
 * Created 23/07/2022 at 10:51
 */
enum class Sort(
    var order: Order = Order.DESC
) {
    POPULARITY(
        Order.DESC
    ),
    RELEASE_DATE(
        Order.DESC
    ),
    REVENUE(
        Order.DESC
    ),
    VOTE_AVERAGE(
        Order.DESC
    );

    override fun toString(): String {
        return when (this) {
            POPULARITY -> {
                "popularity.$order"
            }
            RELEASE_DATE -> {
                "release_date.$order"
            }
            VOTE_AVERAGE -> {
                "vote_average.$order"
            }
            REVENUE -> {
                "revenue.$order"
            }
        }
    }

    enum class Order {
        ASC, DESC;

        override fun toString(): String {
            return when (this) {
                ASC -> "asc"
                DESC -> "desc"
            }
        }


    }
}