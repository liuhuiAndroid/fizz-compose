package com.fizz.compose.repository

import com.fizz.compose.data.FizzResponse
import com.fizz.compose.data.TestJson
import com.fizz.compose.network.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun testJson(map: Map<String, String>): FizzResponse<TestJson> =
        apiService.testJson(map)

}