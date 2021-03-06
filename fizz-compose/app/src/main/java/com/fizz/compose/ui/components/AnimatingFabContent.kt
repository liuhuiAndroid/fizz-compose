package com.fizz.compose.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.util.lerp
import kotlin.math.roundToInt

/**
 * A layout that shows an icon and a text element used as the content for a FAB that extends with
 * an animation.
 */
@Composable
fun AnimatingFabContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    extended: Boolean = true
) {
    val currentState = if (extended) ExpandableFabStates.Extended else ExpandableFabStates.Collapsed
    val transitionDefinition = remember { fabTransitionDefinition() }
    val transition = transition(
        definition = transitionDefinition,
        toState = currentState
    )
    // Using functions instead of Floats here can improve performance, preventing recompositions.
    IconAndTextRow(
        icon,
        text,
        { transition[TextOpacity] },
        { transition[FabWidthFactor] },
        modifier = modifier
    )
}

@Composable
private fun IconAndTextRow(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    opacityProgress: () -> Float, // Functions instead of Floats, to slightly improve performance
    widthProgress: () -> Float,
    modifier: Modifier
) {
    Layout(
        modifier = modifier,
        children = {
            icon()
            Box(modifier = Modifier.drawOpacity(opacityProgress())) {
                text()
            }
        }
    ) { measurables, constraints ->

        val iconPlaceable = measurables[0].measure(constraints)
        val textPlaceable = measurables[1].measure(constraints)

        val height = constraints.maxHeight

        // FAB has an aspect ratio of 1 so the initial width is the height
        val initialWidth = height.toFloat()

        // Use it to get the padding
        val iconPadding = (initialWidth - iconPlaceable.width) / 2f

        // The full width will be : padding + icon + padding + text + padding
        val expandedWidth = iconPlaceable.width + textPlaceable.width + iconPadding * 3

        // Apply the animation factor to go from initialWidth to fullWidth
        val width = lerp(initialWidth, expandedWidth, widthProgress())

        layout(width.roundToInt(), height) {
            iconPlaceable.place(
                iconPadding.roundToInt(),
                constraints.maxHeight / 2 - iconPlaceable.height / 2
            )
            textPlaceable.place(
                (iconPlaceable.width + iconPadding * 2).roundToInt(),
                constraints.maxHeight / 2 - textPlaceable.height / 2
            )
        }
    }
}

private val FabWidthFactor = FloatPropKey("Width")
private val TextOpacity = FloatPropKey("Text Opacity")

private enum class ExpandableFabStates { Collapsed, Extended }

@Suppress("RemoveExplicitTypeArguments")
private fun fabTransitionDefinition(duration: Int = 200) =
    transitionDefinition<ExpandableFabStates> {
        state(ExpandableFabStates.Collapsed) {
            this[FabWidthFactor] = 0f
            this[TextOpacity] = 0f
        }
        state(ExpandableFabStates.Extended) {
            this[FabWidthFactor] = 1f
            this[TextOpacity] = 1f
        }
        transition(
            fromState = ExpandableFabStates.Extended,
            toState = ExpandableFabStates.Collapsed
        ) {
            TextOpacity using tween<Float>(
                easing = LinearEasing,
                durationMillis = (duration / 12f * 5).roundToInt() // 5 out of 12 frames
            )
            FabWidthFactor using tween<Float>(
                easing = FastOutSlowInEasing,
                durationMillis = duration
            )
        }
        transition(
            fromState = ExpandableFabStates.Collapsed,
            toState = ExpandableFabStates.Extended
        ) {
            TextOpacity using tween<Float>(
                easing = LinearEasing,
                delayMillis = (duration / 3f).roundToInt(), // 4 out of 12 frames
                durationMillis = (duration / 12f * 5).roundToInt() // 5 out of 12 frames
            )
            FabWidthFactor using tween<Float>(
                easing = FastOutSlowInEasing,
                durationMillis = duration
            )
        }
    }
