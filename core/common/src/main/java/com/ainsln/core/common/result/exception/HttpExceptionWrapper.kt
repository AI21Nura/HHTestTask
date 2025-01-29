package com.ainsln.core.common.result.exception

data class HttpExceptionWrapper(
    val statusCode: Int,
    val msg: String? = null,
    val excCause: Throwable? = null
) : Exception(msg, excCause)
