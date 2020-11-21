package com.sec.fizz.composables

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sec.fizz.ui.DarkColors

/**
 * 在 Android Studio 中预览可组合函数
 */
@Preview(name = "Android greeting")
@Composable
fun PreviewConstraintLayoutContent() {
    ConstraintLayoutContent()
}

@Composable
fun ConstraintLayoutContent() {
    MaterialTheme(
        colors = DarkColors
    ) {
        ConstraintLayout {
            // Create references for the composables to constrain
            val (button, text) = createRefs()

            Button(
                onClick = { /* Do something */ },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
            ) {
                Text("Button", color = MaterialTheme.colors.primary)
            }

            // Assign reference "text" to the Text composable
            // and constrain it to the bottom of the Button composable
            Text(
                text = "Jetpack Compose",
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(button.bottom, margin = 16.dp)
                },
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}