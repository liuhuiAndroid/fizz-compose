package com.fizz.compose.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FizzResponse<T>(
    val ResultNo: Int,
    val Total: Int = 0,
    val Result: T? = null,
    @Transient
    val pageIndex: Int = 0,
    val Message: String? = null
)