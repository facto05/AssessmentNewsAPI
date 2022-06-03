package iglo.bootcamp.common.entity.base_response

import okhttp3.ResponseBody

sealed class AppResponse<T>(
    val data: T?,
    val exc: Exception?,
    val code: Int?,
    val body: ResponseBody?
) {
    companion object {
        fun <T> success(data: T): AppResponse<T> = AppResponseSuccess(data)
        fun <T> error(exc: Exception, body: ResponseBody?): AppResponse<T> =
            AppResponseError(exc, body, AppResponseError.Error)

        fun <T> loading(): AppResponse<T> = AppResponseLoading()
    }
}

class AppResponseSuccess<T>(data: T) : AppResponse<T>(data, null, null, null)

class AppResponseError<T>(exc: Exception, body: ResponseBody?, code: Int) :
    AppResponse<T>(null, exc, code, body) {
    companion object {
        const val Error = -2
    }
}

class AppResponseLoading<T> : AppResponse<T>(null, null, null, null)