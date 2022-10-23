package com.example.cinemates.model.data
import java.io.Serializable


class Filter private constructor(
    val sortBy: Sort?,
    val withGenres: List<Int>?,
    val withCast: List<Int>?,
) : Serializable {

    data class Builder(
        var sortBy: Sort? = null,
        var withGenres: List<Int> = listOf(),
        var withCast: List<Int> = listOf(),
    ) {
        fun sortBy(sort: Sort?) = apply { this.sortBy = sort }
        fun withGenres(genresId: List<Int>) = apply { this.withGenres = genresId }
        fun withCast(castIds: List<Int>) = apply { this.withCast = castIds }
        fun addCast(castId: Int) = apply { this.withCast += castId }
        fun build() = Filter(sortBy, withGenres, withCast)

    }

    /**
     * Sorting options, default order is Descendant (desc)
     */
    enum class Sort() {
        POPULARITY(), RELEASE_DATE(), REVENUE(), VOTE_AVERAGE();

        private var order: Order = Order.DESC

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

    override fun toString(): String {
        return "sortBy: ${sortBy.toString()}, withGenres: $withGenres"
    }
}
