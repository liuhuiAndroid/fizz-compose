package com.fizz.compose.network

import com.fizz.compose.data.FizzResponse
import com.fizz.compose.data.TestJson
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("testRequest")
    suspend fun testJson(@QueryMap map: Map<String, String>): FizzResponse<TestJson>

}