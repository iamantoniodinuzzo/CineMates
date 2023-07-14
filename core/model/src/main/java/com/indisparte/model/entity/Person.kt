package com.indisparte.model.entity



data class Person(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String
)