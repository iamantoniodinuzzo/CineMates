package com.example.cinemates.domain.model

import com.example.cinemates.util.MediaType
import java.io.Serializable

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
data class Media(
    val mediaType: MediaType,
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val backdropPath: String?
) : Serializable {

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Media

        if (id != other.id) return false

        return true
    }


}
