package com.fizz.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.platform.setContent
import androidx.core.view.WindowCompat
import com.fizz.compose.ui.main.MainContent
import com.fizz.compose.ui.theme.FizzTheme
import com.fizz.compose.ui.utils.SysUiController
import com.fizz.compose.ui.utils.SystemUiController
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            // [Providers] binds values to [ProvidableAmbient] keys. Reading the ambient using [Ambient.current]
            // will return the value provided in [Providers]'s [values] parameter for all composable
            // functions called directly or indirectly in the [children] lambda.
            // [提供者]将值绑定到[ProvidableAmbient]键。 使用[Ambient.current]读取环境将返回[Providers]的[values]
            // 参数中为在[children] lambda中直接或间接调用的所有可组合函数提供的值。
            Providers(SysUiController provides systemUiController) {
                // Insets for Jetpack Compose
                ProvideWindowInsets {
                    MainScreen(onBackPressedDispatcher)
                }
            }
        }
    }
}

@Composable
fun MainScreen(backDispatcher: OnBackPressedDispatcher) {
    FizzTheme {
        Surface {
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
