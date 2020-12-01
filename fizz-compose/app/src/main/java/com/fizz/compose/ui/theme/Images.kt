package com.fizz.compose.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticAmbientOf

/**
 * Images that can vary by theme.
 */
@Immutable
data class Images(@DrawableRes val lockupLogo: Int)

internal val AmbientImages = staticAmbientOf<Images>()
