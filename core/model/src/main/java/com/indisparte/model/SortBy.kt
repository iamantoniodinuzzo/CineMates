package com.indisparte.model

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
