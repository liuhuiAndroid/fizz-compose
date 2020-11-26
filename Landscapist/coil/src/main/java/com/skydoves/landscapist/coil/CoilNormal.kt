package com.skydoves.landscapist.coil

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest

class CoilNormal {

    fun normal(context: Context) {
        val imageLoader = context.imageLoader
        val request = ImageRequest.Builder(context)
                .data("https://www.example.com/image.jpg")
                .target {

                }
                .build()
        val disposable = imageLoader.enqueue(request)
    }
}