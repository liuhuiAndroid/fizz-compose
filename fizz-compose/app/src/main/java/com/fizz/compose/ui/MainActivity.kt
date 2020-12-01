package com.fizz.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.platform.setContent
import androidx.core.view.WindowCompat
import com.fizz.compose.base.FizzScaffold
import com.fizz.compose.ui.main.MainContent
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            // Insets for Jetpack Compose
            ProvideWindowInsets {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    FizzScaffold {
        var splashShown by remember { mutableStateOf(SplashState.Shown) }
        val transition = transition(splashTransitionDefinition, splashShown)
        Box {
            LandingScreen(
                modifier = Modifier.drawOpacity(transition[splashAlphaKey]),
                onTimeout = { splashShown = SplashState.Completed }
            )
            MainContent(
                modifier = Modifier.drawOpacity(transition[contentAlphaKey])
            )
        }
    }
}

enum class SplashState { Shown, Completed }

private val splashAlphaKey = FloatPropKey("Splash alpha")
private val contentAlphaKey = FloatPropKey("Content alpha")

private val splashTransitionDefinition = transitionDefinition<SplashState> {
    state(SplashState.Shown) {
        this[splashAlphaKey] = 1f
        this[contentAlphaKey] = 0f
    }
    state(SplashState.Completed) {
        this[splashAlphaKey] = 0f
        this[contentAlphaKey] = 1f
    }
    transition {
        splashAlphaKey using tween(
            durationMillis = 100
        )
        contentAlphaKey using tween(
            durationMillis = 300
        )
    }
}
