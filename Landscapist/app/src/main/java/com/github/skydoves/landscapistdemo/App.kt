@file:Suppress("unused")

package com.github.skydoves.landscapistdemo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val pipelineConfig =
                OkHttpImagePipelineConfigFactory
                        .newBuilder(this, OkHttpClient.Builder().build())
                        .setDiskCacheEnabled(true)
                        .setDownsampleEnabled(true)
                        .setResizeAndRotateEnabledForNetwork(true)
                        .build()

        Fresco.initialize(this, pipelineConfig)
    }
}
