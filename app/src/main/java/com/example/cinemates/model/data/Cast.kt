package com.example.cinemates.model.data

import java.io.Serializable

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
class Cast(
    var character: String,
    name: String,
    profile_path: String,
    var original_name: String,
    id: Int,
    popularity: Number
) : Person(name, profile_path, id, popularity), Serializable