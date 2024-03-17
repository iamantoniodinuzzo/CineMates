package com.indisparte.media_list

import androidx.annotation.StringRes

data class DefaultMediaList(
    val id: Int = 0,
    val defaultTitle: DefaultTitle,
    val ownerId: Int,
)


enum class DefaultTitle(val defaultTitle: String, @StringRes val defaultTitleRes: Int) {
    TO_SEE(defaultTitle = "to_see", defaultTitleRes = R.string.to_see_default_title),
    SEEN(defaultTitle = "seen", defaultTitleRes = R.string.seen_default_title);

    companion object {
        fun toDefaultTitle(value: String): DefaultTitle? {
            return entries.find { it.defaultTitle.equals(other = value, ignoreCase = true) }
        }
    }
}
