package com.example.cinemates.domain.model.common

import com.example.cinemates.util.Sort
import java.io.Serializable

class Filter constructor(
    val name: String,
    val sortBy: Sort,
    val withGenres: List<Int>,
    val withCast: List<Int>,
) : Serializable {
    var id: Int? = null

    data class Builder(
        var name: String = "",
        var sortBy: Sort = Sort.POPULARITY,
        var withGenres: List<Int> = listOf(),
        var withCast: List<Int> = listOf(),
    ) {
        fun name(name: String) = apply { this.name = name }
        fun sortBy(sort: Sort) = apply { this.sortBy = sort }
        fun withGenres(genresId: List<Int>) = apply { this.withGenres = genresId }
        fun withCast(castIds: List<Int>) = apply { this.withCast = castIds }
        fun build() = Filter(name, sortBy, withGenres, withCast)

    }


    override fun toString(): String {
        return "sortBy: ${sortBy}, withGenres: $withGenres"
    }
}
