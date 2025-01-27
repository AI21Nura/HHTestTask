package com.ainsln.core.common.result

sealed interface DataResult<out T> {
    data object Loading : DataResult<Nothing>
    data class Failure(val e: AppException) : DataResult<Nothing>
    data class Success<T>(val data: T) : DataResult<T>
}
