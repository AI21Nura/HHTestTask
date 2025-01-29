package com.ainsln.core.common.result

import com.ainsln.core.common.result.exception.AppException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface DataResult<out T> {
    data object Loading : DataResult<Nothing>
    data class Failure(val e: AppException) : DataResult<Nothing>
    data class Success<T>(val data: T) : DataResult<T>
}

fun <T> Flow<T>.asFlowResult(
    dispatcher: CoroutineDispatcher,
    handleException: (Throwable) -> AppException,
): Flow<DataResult<T>> {
    return map<T, DataResult<T>> { data -> DataResult.Success(data)}
        .onStart { emit(DataResult.Loading) }
        .catch { e -> emit(DataResult.Failure(handleException(e))) }
        .flowOn(dispatcher)
}
