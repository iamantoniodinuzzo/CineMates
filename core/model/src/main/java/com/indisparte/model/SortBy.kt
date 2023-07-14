package com.indisparte.model

import com.example.cinemates.util.MediaSortOption
import com.example.cinemates.util.Order

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class SortBy(
    var sort: MediaSortOption? = MediaSortOption.Popular,
    var order: Order? = Order.DESC
) {
    override fun toString(): String {
        return "$sort.$order"
    }
}
