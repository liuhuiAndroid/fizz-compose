@file:JvmName("ShimmerParamsAmbient")
@file:JvmMultifileClass
@file:Suppress("unused")

package com.skydoves.landscapist

import androidx.compose.runtime.ambientOf
import androidx.compose.ui.graphics.Color
import com.skydoves.landscapist.ShimmerAmbientProvider.defaultShimmerParams

/**
 * Ambient containing the preferred [ShimmerParams] for providing the same instance
 * in the composable hierarchy.
 */
val ShimmerParamsAmbient = ambientOf { defaultShimmerParams() }

/** A provider for taking the ambient instances related to the [Shimmer]. */
internal object ShimmerAmbientProvider {

    /** Returns the current or default [ShimmerParams] for the `shimmerParams` parameter. */
    fun defaultShimmerParams(): ShimmerParams {
        return ShimmerParams(baseColor = Color.DarkGray, highlightColor = Color.LightGray)
    }
}
