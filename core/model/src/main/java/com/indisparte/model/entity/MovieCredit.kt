package com.indisparte.model.entity


data class MovieCredit(
    val cast: List<PersonAsCast>,
    val crew: List<PersonAsCrew>,
    val personId: Int
)