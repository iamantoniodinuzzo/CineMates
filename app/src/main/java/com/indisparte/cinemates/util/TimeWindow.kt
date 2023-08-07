package com.indisparte.cinemates.util

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:36
 */
enum class TimeWindow(val value:String) {
    WEEK("week"), DAY("day");

    override fun toString(): String {
        return value
    }
}