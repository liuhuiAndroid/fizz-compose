package com.fizz.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.fizz.compose.ui.utils.SysUiController

private val LightOrangeColorPalette = lightColors(
    primary = orange500,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkOrangeColorPalette = darkColors(
    primary = orange200,
    primaryVariant = orange700,
    secondary = teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

@Composable
fun FizzTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
    // 实际上是：val systemUiController = remember { SystemUiController(window) }
    val sysUiController = SysUiController.current
    onCommit(sysUiController, colors.primaryVariant) {
        sysUiController.setSystemBarsColor(
            color = colors.primaryVariant.copy(alpha = AlphaNearOpaque)
        )
    }
    ProvideColors(colors) {
        MaterialTheme(
            colors = colors,
            typography = fizzTypography,
            shapes = shapes,
            content = content
        )
    }
}

object FizzTheme {
    @Composable
    val colors: Colors
        get() = AmbientFizzColors.current
}

@Composable
fun ProvideColors(
    colors: Colors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    Providers(AmbientFizzColors provides colorPalette, children = content)
}

private val AmbientFizzColors = staticAmbientOf<Colors> {
    error("No FizzColorPalette provided")
}