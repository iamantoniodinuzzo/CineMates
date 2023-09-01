package com.indisparte.network.error

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.indisparte.network.R

/**
 *@author Antonio Di Nuzzo
 */
sealed class CineMatesExceptions(
    @StringRes val messageRes: Int,
    @DrawableRes val drawableRes: Int? = null,
) : Exception() {
    data object NoNetworkException :
        CineMatesExceptions(R.string.no_network_exception, R.drawable.ic_no_internet)

    data object EmptyResponse :
        CineMatesExceptions(R.string.empty_response_exception, R.drawable.ic_empty_box)

    data object GenericException : CineMatesExceptions(R.string.something_went_wrong)
}

sealed class ApiException(
    val code: Int,
    @StringRes messageRes: Int,
) : CineMatesExceptions(messageRes) {

    companion object {
        fun fromCode(code: Int): ApiException {
            return ApiException::class.nestedClasses.map { it.objectInstance as ApiException }
                .find { it.code == code } ?: UnknownApiException
        }
    }

    data object UnknownApiException : ApiException(0, R.string.something_went_wrong)
    data object InvalidService : ApiException(2, R.string.invalid_service)

    data object AuthenticationFailed : ApiException(3, R.string.authentication_failed)

    data object InvalidFormat : ApiException(4, R.string.invalid_format)

    data object InvalidParameters : ApiException(5, R.string.invalid_parameters)

    data object InvalidId : ApiException(6, R.string.invalid_id)

    data object InvalidApiKey : ApiException(7, R.string.invalid_api_key)

    data object DuplicateEntry : ApiException(8, R.string.duplicate_entry)

    data object ServiceOffline : ApiException(9, R.string.service_offline)

    data object SuspendedApiKey : ApiException(10, R.string.suspended_api_key)

    data object InternalError : ApiException(11, R.string.internal_error)

    data object ItemUpdated : ApiException(12, R.string.item_updated)

    data object ItemDeleted : ApiException(13, R.string.item_deleted)

    data object Failed : ApiException(15, R.string.failed)

    data object DeviceDenied : ApiException(16, R.string.device_denied)

    data object SessionDenied : ApiException(17, R.string.session_denied)

    data object ValidationFailed : ApiException(18, R.string.validation_failed)

    data object InvalidAcceptHeader : ApiException(19, R.string.invalid_accept_header)

    data object InvalidDateRange : ApiException(20, R.string.invalid_date_range)

    data object EntryNotFound : ApiException(21, R.string.entry_not_found)

    data object InvalidPage : ApiException(22, R.string.invalid_page)

    data object InvalidDate : ApiException(23, R.string.invalid_date)

    data object BackendTimeout : ApiException(24, R.string.backend_timeout)

    data object RequestLimitExceeded : ApiException(25, R.string.request_limit_exceeded)

    data object MissingUsernameAndPassword :
        ApiException(26, R.string.missing_username_and_password)

    data object TooManyRemoteCalls : ApiException(27, R.string.too_many_remote_calls)

    data object InvalidTimezone : ApiException(28, R.string.invalid_timezone)

    data object ConfirmActionRequired : ApiException(29, R.string.confirm_action_required)

    data object InvalidCredentials : ApiException(30, R.string.invalid_credentials)

    data object AccountDisabled : ApiException(31, R.string.account_disabled)

    data object EmailNotVerified : ApiException(32, R.string.email_not_verified)

    data object InvalidRequestToken : ApiException(33, R.string.invalid_request_token)

    data object ResourceNotFound : ApiException(34, R.string.resource_not_found)

    data object InvalidToken : ApiException(35, R.string.invalid_token)

    data object WritePermissionDenied : ApiException(36, R.string.write_permission_denied)

    data object SessionNotFound : ApiException(37, R.string.session_not_found)

    data object EditPermissionDenied : ApiException(38, R.string.edit_permission_denied)

    data object ResourceIsPrivate : ApiException(39, R.string.resource_is_private)

    data object NothingToUpdate : ApiException(40, R.string.nothing_to_update)

    data object RequestTokenNotApproved : ApiException(41, R.string.request_token_not_approved)

    data object UnsupportedRequestMethod : ApiException(42, R.string.unsupported_request_method)

    data object BackendConnectionError : ApiException(43, R.string.backend_connection_error)

    data object UserSuspended : ApiException(45, R.string.user_suspended)

    data object MaintenanceInProgress : ApiException(46, R.string.maintenance_in_progress)

    data object InvalidInput : ApiException(47, R.string.invalid_input)
}

