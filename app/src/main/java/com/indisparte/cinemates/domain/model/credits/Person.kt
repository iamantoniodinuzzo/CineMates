package com.indisparte.cinemates.domain.model.credits

import java.io.Serializable


/**
 * Represents the details of a person
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Person(
    val id: Int ,
    val name: String,
    val profilePath: String
): Serializable

