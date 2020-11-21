package com.sec.fizz.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sec.fizz.R
import com.sec.fizz.ui.FizzcomposeTheme

/**
 * 在 Android Studio 中预览函数
 */
@Preview(showBackground = true)
@Composable
fun ComposeBasicsPreview() {
    ComposeBasics()
}

/**
 * 定义可组合函数
 */
@Composable
fun ComposeBasics() {
    val image = imageResource(R.drawable.header)
    FizzcomposeTheme {
        ScrollableColumn(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val imageModifier = Modifier
                // 指定图片的高度
                .preferredHeight(180.dp)
                // 指定图片的宽度应足以填充所属布局
                .fillMaxWidth()
                // 使用 clip() 函数对图片的四角进行圆角化处理
                .clip(shape = RoundedCornerShape(4.dp))
            Image(
                image, modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            // 添加 Spacer，将图片与标题分开。
            Spacer(Modifier.preferredHeight(16.dp))
            Text(
                "A day in Shark Fin Cove",
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = MaterialTheme.typography.body2
            )
            Text(
                "December 2018",
                style = MaterialTheme.typography.body2
            )
        }
    }
}
