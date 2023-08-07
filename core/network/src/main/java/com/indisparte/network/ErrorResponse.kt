package com.indisparte.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val success: Boolean,
    @SerializedName("status_code")
    val code: Int,
    @SerializedName("status_message")
    val message: String,
)