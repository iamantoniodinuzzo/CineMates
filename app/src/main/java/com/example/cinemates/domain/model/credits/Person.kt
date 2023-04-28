package com.example.cinemates.domain.model.credits

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
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

