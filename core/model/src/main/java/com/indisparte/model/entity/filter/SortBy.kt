package com.indisparte.model.entity.filter

/**
 * @author Antonio Di Nuzzo
 */
data class SortBy(
    var sort: MediaSortOption? = MediaSortOption.Popular,
    var order: Order? = Order.DESC,
) {
    override fun toString(): String {
        return "$sort.$order"
    }
}
