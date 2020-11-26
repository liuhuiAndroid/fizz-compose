package com.skydoves.disneycompose.ui.custom

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.AmbientIndication
import androidx.compose.foundation.Indication
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstrainedLayoutReference
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.OnBalloonInitializedListener
import com.skydoves.disneycompose.ui.theme.purple500
import kotlin.reflect.KClass

@Composable
fun ConstraintLayoutScope.ImageBalloonAnchor(
        reference: ConstrainedLayoutReference,
        modifier: Modifier,
        content: String,
        onClick: (Balloon, View) -> Unit
) {
    val context = ContextAmbient.current
    val lifecycleOwner = LifecycleOwnerAmbient.current
    val balloon = remember { BalloonFactory.create(context, content, lifecycleOwner) }

    BalloonAnchor(
            reference = reference,
            modifier = modifier,
            balloon = balloon,
            onAnchorClick = onClick,
            onClickIndication = RippleIndication(color = purple500)
    )

}

@Composable
fun <T : Balloon.Factory> ConstraintLayoutScope.BalloonAnchor(
        reference: ConstrainedLayoutReference,
        modifier: Modifier = Modifier,
        factory: KClass<T>,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        anchor: View = remember { View(context) },
        onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
        onBalloonClick: (View) -> Unit = { _ -> },
        onBalloonDismiss: () -> Unit = {},
        onBalloonInitialized: (View) -> Unit = { _ -> },
        onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
        onClickIndication: Indication? = AmbientIndication.current(),
        onClickInteractionState: InteractionState = remember { InteractionState() },
        update: (Balloon, View) -> Unit = { _, _ -> }
) {
    BalloonAnchor(
            modifier = constraintAsSquare(
                    modifier = modifier,
                    reference = reference,
                    anchorId = anchor.id
            ),
            factory = factory,
            context = context,
            lifecycleOwner = lifecycleOwner,
            anchor = anchor,
            onAnchorClick = onAnchorClick,
            onBalloonClick = onBalloonClick,
            onBalloonDismiss = onBalloonDismiss,
            onBalloonInitialized = onBalloonInitialized,
            onBalloonOutsideTouch = onBalloonOutsideTouch,
            onClickIndication = onClickIndication,
            onClickInteractionState = onClickInteractionState,
            update = update
    )
}

/**
 * An anchor of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.
 *
 * This composable only works in ConstraintLayout,
 * and must be placed in the below the reference composable.
 *
 * ```
 * BalloonAnchor(
 *   reference = image,
 *   modifier = Modifier.aspectRatio(0.8f),
 *   balloon = balloonUtils.getTitleBalloon(
 *     title = poster.name,
 *     context = context,
 *     lifecycle = lifecycleOwner),
 *   onAnchorClick = { balloon, anchor -> balloon.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> },
 *   onClickIndication = RippleIndication(color = purple500)
 * )
 * ```
 */
@Composable
fun ConstraintLayoutScope.BalloonAnchor(
        reference: ConstrainedLayoutReference,
        modifier: Modifier = Modifier,
        balloon: Balloon,
        context: Context = ContextAmbient.current,
        anchor: View = remember { View(context) },
        onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
        onBalloonClick: (View) -> Unit = { _ -> },
        onBalloonDismiss: () -> Unit = {},
        onBalloonInitialized: (View) -> Unit = { _ -> },
        onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
        onClickIndication: Indication? = AmbientIndication.current(),
        onClickInteractionState: InteractionState = remember { InteractionState() },
        update: (Balloon, View) -> Unit = { _, _ -> }
) {
    BalloonAnchor(
            modifier = constraintAsSquare(
                    modifier = modifier,
                    reference = reference,
                    anchorId = anchor.id
            ),
            balloon = balloon,
            context = context,
            anchor = anchor,
            onAnchorClick = onAnchorClick,
            onBalloonClick = onBalloonClick,
            onBalloonDismiss = onBalloonDismiss,
            onBalloonInitialized = onBalloonInitialized,
            onBalloonOutsideTouch = onBalloonOutsideTouch,
            onClickIndication = onClickIndication,
            onClickInteractionState = onClickInteractionState,
            update = update
    )
}

/**
 * An anchor of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.Factory class.
 *
 * ```
 * BalloonAnchor(
 *   modifier = Modifier.aspectRatio(0.8f),
 *   factory = MyBalloonFactory::class,
 *   onAnchorClick = { balloon, anchor -> balloon.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> },
 *   onClickIndication = RippleIndication(color = purple500)
 * )
 * ```
 */
@Composable
fun <T : Balloon.Factory> BalloonAnchor(
        modifier: Modifier = Modifier,
        factory: KClass<T>,
        context: Context = ContextAmbient.current,
        lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
        anchor: View = remember { View(context) },
        onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
        onBalloonClick: (View) -> Unit = { _ -> },
        onBalloonDismiss: () -> Unit = {},
        onBalloonInitialized: (View) -> Unit = { _ -> },
        onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
        onClickIndication: Indication? = AmbientIndication.current(),
        onClickInteractionState: InteractionState = remember { InteractionState() },
        update: (Balloon, View) -> Unit = { _, _ -> }
) {
    val instance: T = remember { factory::java.get().newInstance() }
    val balloon: Balloon = remember { instance.create(context, lifecycleOwner) }
    BalloonAnchor(
            modifier = modifier,
            balloon = balloon,
            context = context,
            anchor = anchor,
            onAnchorClick = onAnchorClick,
            onBalloonClick = onBalloonClick,
            onBalloonDismiss = onBalloonDismiss,
            onBalloonInitialized = onBalloonInitialized,
            onBalloonOutsideTouch = onBalloonOutsideTouch,
            onClickIndication = onClickIndication,
            onClickInteractionState = onClickInteractionState,
            update = update
    )
}

/*
 * Create an anchor composable of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.
 * https://github.com/skydoves/balloon
 *
 * ```
 * BalloonAnchor(
 *   modifier = Modifier.aspectRatio(0.8f),
 *   balloon = balloonUtils.getTitleBalloon(
 *     title = poster.name,
 *     context = context,
 *     lifecycle = lifecycleOwner),
 *   onAnchorClick = { balloon, anchor -> balloon.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> },
 *   onClickIndication = RippleIndication(color = purple500)
 * )
 * ```
 */
@Composable
fun BalloonAnchor(
        modifier: Modifier = Modifier,
        balloon: Balloon,
        context: Context = ContextAmbient.current,
        anchor: View = remember { View(context) },
        onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
        onBalloonClick: (View) -> Unit = { _ -> },
        onBalloonDismiss: () -> Unit = {},
        onBalloonInitialized: (View) -> Unit = { _ -> },
        onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
        onClickIndication: Indication? = AmbientIndication.current(),
        onClickInteractionState: InteractionState = remember { InteractionState() },
        update: (Balloon, View) -> Unit = { _, _ -> }
) {
    // initialize Balloon.
    balloon.setOnBalloonClickListener { onBalloonClick(it) }
    balloon.setOnBalloonDismissListener { onBalloonDismiss() }
    balloon.onBalloonInitializedListener = OnBalloonInitializedListener { onBalloonInitialized(it) }
    balloon.setOnBalloonOutsideTouchListener { view, motionEvent ->
        onBalloonOutsideTouch(view, motionEvent)
    }

    // draw anchor of the Balloon and updates.
    AndroidView(
            viewBlock = { anchor },
            modifier = Modifier.clickable(
                    indication = onClickIndication,
                    interactionState = onClickInteractionState,
                    onClick = { onAnchorClick(balloon, anchor) }
            ).then(modifier)
    ) {
        update(balloon, it)
    }
}

private fun ConstraintLayoutScope.constraintAsSquare(
        reference: ConstrainedLayoutReference,
        modifier: Modifier,
        anchorId: Int
): Modifier {
    return modifier.constrainAs(ConstrainedLayoutReference(anchorId)) {
        start.linkTo(reference.start)
        end.linkTo(reference.end)
        top.linkTo(reference.top)
        bottom.linkTo(reference.bottom)
    }
}