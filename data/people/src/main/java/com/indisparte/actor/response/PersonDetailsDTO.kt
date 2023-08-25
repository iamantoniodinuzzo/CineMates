package com.indisparte.actor.response


import com.google.gson.annotations.SerializedName
import com.indisparte.response.ImagesResponseDTO

data class PersonDetailsDTO(
    val adult: Boolean,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String?,
    @SerializedName("deathday")
    val deathDay: String?,
    val gender: Int,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String?,
    val images : ImagesResponseDTO
)