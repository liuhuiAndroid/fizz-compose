package com.fizz.compose.ui.components

import androidx.compose.animation.animate
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.vector.VectorAsset

/**
 * 带动画的Icon
 */
@Composable
fun ScaleIcon(
    state: Boolean,
    asset: VectorAsset,
    scaleXY: Float,
    duration: Int,
    modifier: Modifier = Modifier
) {
    Icon(
        asset = asset,
        modifier = modifier
            .drawLayer(
                scaleX = animate(if (state) scaleXY else 0.9f, tween(duration)),
                scaleY = animate(if (state) scaleXY else 0.9f, tween(duration))
            )
    )
}
