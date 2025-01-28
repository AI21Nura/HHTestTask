package com.ainsln.core.network.retrofit

import com.ainsln.core.network.model.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("u/0/uc")
    suspend fun get(
        @Query("id") id: String = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
        @Query("export") export: String = "download"
    ): Result<ResponseDTO>
}
