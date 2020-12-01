package com.fizz.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fizz.compose.R

private val YellowThemeLight = lightColors(
    primary = yellow500,
    primaryVariant = yellow400,
    onPrimary = Color.Black,
    secondary = blue700,
    secondaryVariant = blue800,
    onSecondary = Color.White
)

private val YellowThemeDark = darkColors(
    primary = yellow200,
    secondary = blue200,
    onSecondary = Color.Black,
    surface = yellowDarkPrimary
)

@Composable
fun YellowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        YellowThemeDark
    } else {
        YellowThemeLight
    }
    FizzTheme(darkTheme, colors, content)
}

private val BlueThemeLight = lightColors(
    primary = blue700,
    onPrimary = Color.White,
    primaryVariant = blue800,
    secondary = yellow500
)

private val BlueThemeDark = darkColors(
    primary = blue200,
    secondary = yellow200,
    surface = blueDarkPrimary
)

@Composable
fun BlueTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        BlueThemeDark
    } else {
        BlueThemeLight
    }
    FizzTheme(darkTheme, colors, content)
}

private val PinkThemeLight = lightColors(
    primary = pink500,
    secondary = pink500,
    primaryVariant = pink600,
    onPrimary = Color.Black
)

private val PinkThemeDark = darkColors(
    primary = pink200,
    secondary = pink200,
    surface = pinkDarkPrimary
)

@Composable
fun PinkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        PinkThemeDark
    } else {
        PinkThemeLight
    }
    FizzTheme(darkTheme, colors, content)
}

private val LightElevation = Elevations()

private val DarkElevation = Elevations(card = 1.dp)

private val LightImages = Images(lockupLogo = R.drawable.ic_lockup_blue)

private val DarkImages = Images(lockupLogo = R.drawable.ic_lockup_white)

@Composable
private fun FizzTheme(
    darkTheme: Boolean,
    colors: Colors,
    content: @Composable () -> Unit
) {
    val elevation = if (darkTheme) DarkElevation else LightElevation
    val images = if (darkTheme) DarkImages else LightImages
    Providers(
        AmbientElevations provides elevation,
        AmbientImages provides images
    ) {
        MaterialTheme(
            colors = colors,
            typography = fizzTypography,
            shapes = shapes,
            content = content
        )
    }
}

val fizzColors = lightColors(
    primary = fizz_purple_800,
    secondary = fizz_red,
    surface = fizz_purple_900,
    onSurface = fizz_white,
    primaryVariant = fizz_purple_700
)

@Composable
fun FizzTheme(children: @Composable () -> Unit) {
    MaterialTheme(colors = fizzColors, typography = fizzTypography) {
        children()
    }
}


/**
 * Alternate to [MaterialTheme] allowing us to add our own theme systems (e.g. [Elevations]) or to
 * extend [MaterialTheme]'s types e.g. return our own [Colors] extension
 */
object FizzTheme {

    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val colors: Colors
        get() = MaterialTheme.colors

    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val typography: Typography
        get() = MaterialTheme.typography

    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val shapes: Shapes
        get() = MaterialTheme.shapes

    /**
     * Retrieves the current [Elevations] at the call site's position in the hierarchy.
     */
    @Composable
    val elevations: Elevations
        get() = AmbientElevations.current

    /**
     * Retrieves the current [Images] at the call site's position in the hierarchy.
     */
    @Composable
    val images: Images
        get() = AmbientImages.current
}
