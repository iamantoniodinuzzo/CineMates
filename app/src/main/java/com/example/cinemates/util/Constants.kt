package com.example.cinemates.util

import android.graphics.Color
import com.example.cinemates.BuildConfig
import com.example.cinemates.R
import java.util.*

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:48
 */
const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
private val mRnd = Random()
const val YT_API_KEY = BuildConfig.YT_API_KEY
const val BASE_URL = "https://api.themoviedb.org/3/"
val DEFAULT_SYSTEM_LANGUAGE = Locale.getDefault().language
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"
const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"
const val Attribution =
    "This product uses the TMDb API but is not endorsed or certified by TMDb."
const val Popular = "Popular"
const val Upcoming = "Upcoming"
const val Current = "Current"
const val TopRated = "TopRated"
const val YOUTUBE_COM_WATCH_V = "http://www.youtube.com/watch?v="

//TODO maybe this color can be customizable
// This is the base color which will be mixed with the generated one
val randomColor: Int
    get() {
        // This is the base color which will be mixed with the generated one
        val baseColor = R.color.vermilion_100 //TODO maybe this color can be customizable
        val baseRed = Color.red(baseColor)
        val baseGreen = Color.green(baseColor)
        val baseBlue = Color.blue(baseColor)
        val red = (baseRed + mRnd.nextInt(256)) / 2
        val green = (baseGreen + mRnd.nextInt(256)) / 2
        val blue = (baseBlue + mRnd.nextInt(256)) / 2
        return Color.rgb(red, green, blue)
    }
