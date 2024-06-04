package net.hicare.core.common

import com.google.gson.annotations.SerializedName

class ServerError(
    val statusCode: Int,
    val message: String,
    val details: List<String>
)

fun DtoErrorResponse.toDomainModel() = ServerError(
    statusCode = statusCode ?: 0,
    message = message.orEmpty(),
    details = details?.map { it.orEmpty() }.orEmpty()
)

class DtoErrorResponse(
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("details")
    val details: List<String?>?
)