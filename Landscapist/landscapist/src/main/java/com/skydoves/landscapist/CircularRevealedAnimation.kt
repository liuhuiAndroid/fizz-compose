package com.skydoves.landscapist

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween

/**
 * CircularRevealedAnimation is an animation for animating a clipping circle to reveal an image.
 * The animation has two states Loaded, Empty.
 */
internal object CircularRevealedAnimation {

    /** Common interface of the animation states. */
    enum class State {
        /** animation not started. */
        None,

        /** animation finished. */
        Finished
    }

    val Radius = FloatPropKey()

    /** definitions of the specific animating values based on animation states.  */
    fun definition(durationMillis: Int, easing: Easing = LinearEasing) = transitionDefinition<State> {
        state(State.None) {
            this[Radius] = 0f
        }
        state(State.Finished) {
            this[Radius] = 1f
        }

        transition {
            Radius using tween(durationMillis = durationMillis, easing = easing)
        }
    }
}
