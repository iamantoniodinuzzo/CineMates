package com.example.cinemates.util

import androidx.annotation.StringRes
import com.example.cinemates.R

/**
 * An enumeration representing the various types of TV shows.
 *
 * This enumeration provides a convenient way to represent the various types of TV shows,
 * including documentary, news, miniseries, reality, scripted, talk show, and video. Each
 * value in the enumeration represents a specific TV show type and has an integer ID, a
 * string value, and a string resource ID. The ID and value properties can be used to
 * identify and filter TV shows by type, while the nameResId property can be used to display
 * the name of the type in the user interface.

 * @property id The integer ID of the TV show type.
 * @property value The string value of the TV show type.
 * @property nameResId The string resource ID of the TV show type's name.
 * @author Antonio Di Nuzzo (Indisparte)
 */
enum class TvType(val id: Int, val value: String, @StringRes val nameResId: Int) {
    DOCUMENTARY(0, "documentary", R.string.tv_type_documentary),
    NEWS(1, "news", R.string.tv_type_news),
    MINISERIES(2, "miniseries", R.string.tv_type_miniseries),
    REALITY(3, "reality", R.string.tv_type_reality),
    SCRIPTED(4, "scripted", R.string.tv_type_scripted),
    TALK_SHOW(5, "talk_show", R.string.tv_type_talk_show),
    VIDEO(6, "video", R.string.tv_type_video);

    override fun toString(): String {
        return value
    }


}