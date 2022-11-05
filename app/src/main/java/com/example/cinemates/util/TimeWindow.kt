package com.example.cinemates.util

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:36
 */
enum class TimeWindow {
    WEEK, DAY;

    override fun toString(): String {
        return when(this){
            WEEK -> "week"
            DAY -> "day"
        }
    }
}