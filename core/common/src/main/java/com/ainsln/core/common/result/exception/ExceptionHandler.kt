package com.ainsln.core.common.result.exception

import android.database.sqlite.SQLiteException
import com.ainsln.core.common.utils.Logger
import java.io.IOException
import javax.inject.Inject

interface ExceptionHandler {
    fun handle(e: Throwable): AppException
}

internal class BaseExceptionHandler @Inject constructor(
    private val logger: Logger
) : ExceptionHandler {

    override fun handle(e: Throwable): AppException {
        return when(e){
            is HttpExceptionWrapper -> {
                logger.e(LOG_TAG, "HttpException. Message: ${e.message}")
                AppException.NetworkError(e.statusCode, e.msg)
            }
            is SQLiteException -> {
                logger.e(LOG_TAG, "SQLiteException. Message: ${e.message}")
                AppException.DatabaseError(e.message)
            }
            is IOException -> {
                logger.e(LOG_TAG, "IOException. Message: ${e.message}")
                AppException.NetworkError(code = 0, msg = e.message)
            }
            else -> {
                logger.e(LOG_TAG, "UnknownError. Message: ${e.message}")
                AppException.UnknownError(e.message)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "BaseExceptionHandler"
    }
}
