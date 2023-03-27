package com.example.cinemates.model

import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class Media(
    val backdrop_path: String,
    val genres: List<Genre>,
    val id: Int,
    val original_language: String,
    val poster_path: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val title: String,
    var personalStatus: PersonalStatus = PersonalStatus.EMPTY,
    var favorite: Boolean = false,
    val tagline: String,
    val status: String,
    val overview: String,
    val homepage: String,
    val production_companies: List<ProductionCompany>?= listOf(),
    val production_countries: List<ProductionCountry>?= listOf()
) :java.io.Serializable{
    abstract fun getType(): MediaType

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Media) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
enum class PersonalStatus(
    val status: Int
) {
    TO_SEE(1), SEEN(0), EMPTY(2);
}