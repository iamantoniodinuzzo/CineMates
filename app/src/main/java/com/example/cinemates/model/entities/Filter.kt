package com.example.cinemates.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cinemates.util.Converters
import com.example.cinemates.util.Sort
import java.io.Serializable

@Entity
class Filter constructor(
    val name: String,
    @TypeConverters(Converters::class)
    val sortBy: Sort,
    @TypeConverters(Converters::class)
    val withGenres: List<Int>,
    @TypeConverters(Converters::class)
    val withCast: List<Int>,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
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
