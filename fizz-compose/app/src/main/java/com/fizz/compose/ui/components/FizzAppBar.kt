package com.fizz.compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fizz.compose.R
import com.fizz.compose.ui.theme.FizzTheme
import com.fizz.compose.ui.theme.elevatedSurface

@Composable
fun FizzAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = { },
    title: @Composable RowScope.() -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        val backgroundColor = MaterialTheme.colors.elevatedSurface(3.dp)
        TopAppBar(
            modifier = modifier,
            backgroundColor = backgroundColor.copy(alpha = 0.95f),
            elevation = 0.dp, // No shadow needed
            contentColor = MaterialTheme.colors.onSurface,
            actions = actions,
            title = { Row { title() } },
            navigationIcon = {
                Image(
                    asset = vectorResource(id = R.drawable.ic_fizz),
                    modifier = Modifier
                        .clickable(onClick = onNavIconPressed)
                        .padding(horizontal = 16.dp)
                )
            }
        )
        Divider()
    }
}

@Preview
@Composable
fun FizzAppBarPreview() {
    FizzTheme {
        FizzAppBar(title = { Text("Preview!") })
    }
}

@Preview
@Composable
fun FizzAppBarPreviewDark() {
    FizzTheme(isDarkTheme = true) {
        FizzAppBar(title = { Text("Preview!") })
    }
}
