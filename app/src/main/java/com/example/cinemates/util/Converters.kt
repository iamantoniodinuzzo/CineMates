package com.example.cinemates.util

import androidx.room.TypeConverter
import com.example.cinemates.model.data.Collection
import com.example.cinemates.model.data.Genre
import com.example.cinemates.model.data.PersonalStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:47
 */
class Converters {
    @TypeConverter
    fun toPersonalStatus(status: Int): PersonalStatus {
        return when (status) {
            PersonalStatus.TO_SEE.status -> {
                PersonalStatus.TO_SEE
            }
            PersonalStatus.SEEN.status -> {
                PersonalStatus.SEEN
            }
            PersonalStatus.EMPTY.status -> {
                PersonalStatus.EMPTY
            }
            else -> throw IllegalArgumentException("Could not recognize status")
        }
    }

    @TypeConverter
    fun toInteger(status: PersonalStatus): Int {
        return status.status
    }

    @TypeConverter
    fun fromString(value: String?): List<Genre> {
        val listType = object : TypeToken<ArrayList<Genre?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Genre?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromValue(value: String?): Collection {
        val listType = object : TypeToken<Collection?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCollection(list: Collection?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
