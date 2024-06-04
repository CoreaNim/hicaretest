package net.hicare.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.hicare.core.network.util.DtoErrorResponse
import net.hicare.core.network.util.ResultWrapper
import net.hicare.core.network.util.toDomainModel
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend (CoroutineScope) -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke(this))
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    try {
                        throwable.response()?.errorBody()?.string()?.let {
                            val type = object : TypeToken<DtoErrorResponse>() {}.type
                            val errorResponse: DtoErrorResponse = Gson().fromJson(it, type)
                            return@withContext ResultWrapper.AppServerError(errorResponse.toDomainModel())
                        }
                        ResultWrapper.GenericError(throwable)
                    } catch (exception: Exception) {
                        ResultWrapper.GenericError(exception)
                    }
                }

                else -> {
                    ResultWrapper.GenericError(throwable)
                }
            }
        } finally {
        }
    }
}
