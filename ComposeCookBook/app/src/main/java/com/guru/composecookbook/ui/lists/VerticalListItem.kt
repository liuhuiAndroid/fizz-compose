package com.guru.composecookbook.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun VerticalListItem(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {

        val imageModifier = Modifier
            // 定义首选高度
            .preferredHeight(150.dp)
            .fillMaxWidth()
            // MaterialTheme.shapes.medium: Shape used by medium components like [Card] or [AlertDialog].
            .clip(shape = MaterialTheme.shapes.medium)

        Image(
            // Synchronously load an image resource.
            imageResource(item.imageId),
            modifier = imageModifier,
            // 缩放规则
            contentScale = ContentScale.Crop
        )
        // an empty space layout
        Spacer(Modifier.preferredHeight(16.dp))
        Text(
            text = item.title,
            style = typography.h6
        )
        Text(
            text = item.subtitle,
            style = typography.body2
        )
        Text(
            text = item.source,
            style = typography.subtitle2
        )
    }
}

@Preview
@Composable
fun PreviewVerticalListItem() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        VerticalListItem(item = item)
    }
}