package com.example.cinemates.model

class Crew(
    adult: Boolean,
    gender: Int?,
    id: Int,
    known_for_department: String,
    name: String,
    popularity: Double,
    profile_path: String?,
    val credit_id: String,
    val department: String,
    val job: String,
    val original_name: String
) : Person(
    adult = adult,
    gender = gender,
    id = id,
    known_for_department = known_for_department,
    name = name,
    popularity = popularity,
    profile_path = profile_path
){
    override fun toString(): String {
        return "Crew(credit_id='$credit_id', department='$department', job='$job', original_name='$original_name')"
    }
}