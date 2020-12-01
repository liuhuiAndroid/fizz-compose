package com.fizz.compose.ui.components

import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AmbientContentColor
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fizz.compose.ui.theme.FizzTheme

@Composable
fun FizzBottomNavigationItem(
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit = emptyContent(),
    alwaysShowLabels: Boolean = true,
    interactionState: InteractionState = remember { InteractionState() },
    selectedContentColor: Color = AmbientContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {
    FizzBottomNavigationItem(
        icon = icon,
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        label = label,
        alwaysShowLabels = alwaysShowLabels,
        interactionState = interactionState,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
    )
}