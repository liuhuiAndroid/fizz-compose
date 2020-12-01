package com.fizz.compose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
        small = RoundedCornerShape(percent = 50),
        medium = RoundedCornerShape(size = 0f),
        large = RoundedCornerShape(
                topLeft = 16.dp,
                topRight = 0.dp,
                bottomRight = 0.dp,
                bottomLeft = 16.dp
        )
)
