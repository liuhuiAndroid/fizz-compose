package com.fizz.compose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fizz.compose.ui.theme.FizzTheme

@Composable
fun FizzBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FizzTheme.colors.uiBackground,
    contentColor: Color = FizzTheme.colors.textPrimary,
    elevation: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content,
    )
}