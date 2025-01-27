package com.ainsln.core.common.result

sealed interface AppException {
    data class NetworkError(val code: Int, val msg: String?) : AppException
    data class DatabaseError(val msg: String?) : AppException
    data class UnknownError(val msg: String?) : AppException
}
