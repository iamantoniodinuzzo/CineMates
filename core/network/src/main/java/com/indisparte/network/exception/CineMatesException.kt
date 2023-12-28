package com.indisparte.network.exception

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.indisparte.network.R

/**
 *@author Antonio Di Nuzzo
 */
sealed class CineMatesException(
    @StringRes val messageRes: Int,
    @DrawableRes val drawableRes: Int? = null,
) : Exception() {
    data object NoNetworkException :
        CineMatesException(R.string.no_network_exception, R.drawable.ic_no_internet)

    data object EmptyResponse :
        CineMatesException(R.string.empty_response_exception, R.drawable.ic_empty_box)

    data object GenericException : CineMatesException(R.string.something_went_wrong)
}