package com.indisparte.database.util

import androidx.room.TypeConverter
import java.util.Date


/**
 *@author Antonio Di Nuzzo
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}