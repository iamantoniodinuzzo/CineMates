package com.indisparte.cinemates.domain.model.common

import com.indisparte.cinemates.util.MediaSortOption
import com.indisparte.cinemates.util.Order

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class SortBy(
    var sort: MediaSortOption? = MediaSortOption.Popular,
    var order: Order? = Order.DESC,
) {
    override fun toString(): String {
        return "$sort.$order"
    }
}
