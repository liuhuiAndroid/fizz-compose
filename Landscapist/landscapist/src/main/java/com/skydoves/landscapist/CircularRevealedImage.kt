@file:JvmName("CircularRevealedImage")
@file:JvmMultifileClass

package com.skydoves.landscapist

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.painter.ImagePainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient

/**
 * CircularRevealedImage is an image composable for animating a clipping circle to reveal when loading an image.
 *
 * @param asset an image asset for loading for the content.
 * @param imagePainter an image painter to draw an [ImageAsset] into the provided canvas.
 * @param modifier adjust the drawing image layout or drawing decoration of the content.
 * @param alignment alignment parameter used to place the loaded [ImageAsset] in the
 * given bounds defined by the width and height.
 * @param contentScale parameter used to determine the aspect ratio scaling to be
 * used if the bounds are a different size from the intrinsic size of the loaded [ImageAsset].
 * @param alpha an alpha value to apply for the image when it is rendered onscreen.
 * @param colorFilter colorFilter to apply for the image when it is rendered onscreen.
 * @param circularRevealedEnabled a conditional value for enabling or not the circular revealing animation.
 * @param circularRevealedDuration milli-second times from start to finish animation.
 * @param clock an interface allows AnimationClock to be subscribed and unsubscribed.
 */
@Composable
fun CircularRevealedImage(
        asset: ImageAsset,
        imagePainter: Painter = ImagePainter(asset),
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Crop,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        circularRevealedEnabled: Boolean = false,
        circularRevealedDuration: Int = DefaultCircularRevealedDuration,
        clock: AnimationClockObservable = AnimationClockAmbient.current.asDisposableClock(),
) {
    val circularRevealedPainter = remember(imagePainter) {
        CircularRevealedPainter(
                asset,
                imagePainter,
                clock,
                circularRevealedDuration
        ).also { it.start() }
    }
    Image(
            painter = if (circularRevealedEnabled) {
                circularRevealedPainter.getMainPainter()
            } else {
                imagePainter
            },
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            colorFilter = colorFilter,
            alpha = alpha
    )
}

/** a definition of the default circular revealed animations duration. */
const val DefaultCircularRevealedDuration = 350
