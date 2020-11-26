package com.skydoves.landscapist.coil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.platform.ContextAmbient
import coil.ImageLoader
import coil.imageLoader

/**
 * Ambient containing the preferred [ImageLoader] for providing the same instance
 * in our composable hierarchy.
 */
val CoilImageLoaderAmbient = staticAmbientOf<ImageLoader?> { null }

/** A provider for taking the ambient instances related to the `CoilImage`. */
internal object CoilAmbientProvider {

    /** Returns the current or default [ImageLoader] for the `ColiImage` parameter. */
    @Composable
    fun getCoilImageLoader(): ImageLoader {
        return CoilImageLoaderAmbient.current ?: ContextAmbient.current.imageLoader
    }
}
