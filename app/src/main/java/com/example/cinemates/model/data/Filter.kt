package com.example.cinemates.model.data

import java.io.Serializable

/**
 * @author Antonio Di Nuzzo
 * Created 01/09/2022
 */
data class Filter(
    var sortBy: Sort?,
    var withGenres: String?
) : Serializable {

    /**
     * Sorting options, default order is Ascendant (asc)
     */
    enum class Sort(private var order: Order) {
        POPULARITY(Order.DESC), RELEASE_DATE(Order.DESC), REVENUE(Order.DESC), VOTE_AVERAGE(Order.DESC);

        override fun toString(): String {
            return this.name.lowercase() + "." + this.order.name.lowercase()
        }

        fun setOrder(order: Order) {
            this.order = order
        }

        enum class Order {
            ASC, DESC
        }

    }
}
