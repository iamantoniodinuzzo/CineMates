package com.example.cinemates.model

import com.google.gson.annotations.SerializedName

class CreatedBy(
     creditId: String,
    gender: Int,
    id: Int,
    name: String,
    profilePath: String
) : Cast(creditId, gender, id, name, profilePath)
