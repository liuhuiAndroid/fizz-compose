package com.fizz.compose.ui.theme

import androidx.compose.material.AmbientElevationOverlay
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp

val orange200 = Color(0xFFff7961)
val orange500 = Color(0xFFf44336)
val orange700 = Color(0xFFba000d)

val teal200 = Color(0xff80deea)

const val AlphaNearOpaque = 0.95f

val Shadow11 = Color(0xff001787)
val Shadow10 = Color(0xff00119e)
val Shadow9 = Color(0xff0009b3)
val Shadow8 = Color(0xff0200c7)
val Shadow7 = Color(0xff0e00d7)
val Shadow6 = Color(0xff2a13e4)
val Shadow5 = Color(0xff4b30ed)
val Shadow4 = Color(0xff7057f5)
val Shadow3 = Color(0xff9b86fa)
val Shadow2 = Color(0xffc8bbfd)
val Shadow1 = Color(0xffded6fe)
val Shadow0 = Color(0xfff4f2ff)

val Ocean11 = Color(0xff005687)
val Ocean10 = Color(0xff006d9e)
val Ocean9 = Color(0xff0087b3)
val Ocean8 = Color(0xff00a1c7)
val Ocean7 = Color(0xff00b9d7)
val Ocean6 = Color(0xff13d0e4)
val Ocean5 = Color(0xff30e2ed)
val Ocean4 = Color(0xff57eff5)
val Ocean3 = Color(0xff86f7fa)
val Ocean2 = Color(0xffbbfdfd)
val Ocean1 = Color(0xffd6fefe)
val Ocean0 = Color(0xfff2ffff)

val Lavender11 = Color(0xff170085)
val Lavender10 = Color(0xff23009e)
val Lavender9 = Color(0xff3300b3)
val Lavender8 = Color(0xff4400c7)
val Lavender7 = Color(0xff5500d7)
val Lavender6 = Color(0xff6f13e4)
val Lavender5 = Color(0xff8a30ed)
val Lavender4 = Color(0xffa557f5)
val Lavender3 = Color(0xffc186fa)
val Lavender2 = Color(0xffdebbfd)
val Lavender1 = Color(0xffebd6fe)
val Lavender0 = Color(0xfff9f2ff)

val Rose11 = Color(0xff7f0054)
val Rose10 = Color(0xff97005c)
val Rose9 = Color(0xffaf0060)
val Rose8 = Color(0xffc30060)
val Rose7 = Color(0xffd4005d)
val Rose6 = Color(0xffe21365)
val Rose5 = Color(0xffec3074)
val Rose4 = Color(0xfff4568b)
val Rose3 = Color(0xfff985aa)
val Rose2 = Color(0xfffdbbcf)
val Rose1 = Color(0xfffed6e2)
val Rose0 = Color(0xfffff2f6)

val Neutral8 = Color(0xff121212)
val Neutral7 = Color(0xdef000000)
val Neutral6 = Color(0x99000000)
val Neutral5 = Color(0x61000000)
val Neutral4 = Color(0x1f000000)
val Neutral3 = Color(0x1fffffff)
val Neutral2 = Color(0x61ffffff)
val Neutral1 = Color(0xbdffffff)
val Neutral0 = Color(0xffffffff)

val FunctionalRed = Color(0xffd00036)
val FunctionalRedDark = Color(0xffea6d7e)
val FunctionalGreen = Color(0xff52c41a)
val FunctionalGrey = Color(0xfff6f6f6)
val FunctionalDarkGrey = Color(0xff2e2e2e)

val colorBrand = Shadow5
val colorUiBackground = Neutral0
val colorUiBorder = Neutral4
val colorUiFloated = FunctionalGrey
val colorTextSecondary = Neutral7
val colorTextHelp = Neutral6
val colorTextInteractive = Neutral0
val colorTextLink = Ocean11
val colorIconSecondary = Neutral7
val colorIconInteractive = Neutral0
val colorIconInteractiveInactive = Neutral1
val colorError = FunctionalRed
val colorGradient6_1 = listOf(Shadow4, Ocean3, Shadow2, Ocean3, Shadow4)
val colorGradient6_2 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4)
val colorGradient3_1 = listOf(Shadow2, Ocean3, Shadow4)
val colorGradient3_2 = listOf(Rose2, Lavender3, Rose4)
val colorGradient2_1 = listOf(Shadow4, Shadow11)
val colorGradient2_2 = listOf(Ocean3, Shadow3)

/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

/**
 * Calculates the color of an elevated `surface` in dark mode. Returns `surface` in light mode.
 */
@Composable
fun Colors.elevatedSurface(elevation: Dp): Color {
    return AmbientElevationOverlay.current?.apply(
        color = this.surface,
        elevation = elevation
    ) ?: this.surface
}
