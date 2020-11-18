package com.sec.fizz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sec.fizz.ui.DarkColors
import com.sec.fizz.ui.FizzcomposeTheme

/**
 * Jetpack Compose 基础知识
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutContent()
        }
    }
}

/**
 * 定义可组合函数
 */
@Composable
fun NewsStory(onClick: () -> Unit) {
    val image = imageResource(R.drawable.header)
    MaterialTheme {
        ScrollableColumn(
            Modifier
                .clickable(onClick = onClick)
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
            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw, and a few of the sights I saw, and a few of the sights I saw ",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = typography.body2
            )
            Text(
                "December 2018",
                style = typography.body2
            )

            Image(
                image, modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw, and a few of the sights I saw, and a few of the sights I saw ",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = typography.body2
            )
            Text(
                "December 2018",
                style = typography.body2
            )

            Image(
                image, modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw, and a few of the sights I saw, and a few of the sights I saw ",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = typography.body2
            )
            Text(
                "December 2018",
                style = typography.body2
            )

            Image(
                image, modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw, and a few of the sights I saw, and a few of the sights I saw ",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = typography.body2
            )
            Text(
                "December 2018",
                style = typography.body2
            )
        }
    }
}

/**
 * 在 Android Studio 中预览可组合函数
 */
@Preview(name = "Android greeting")
@Composable
fun PreviewGreeting() {
    ConstraintLayoutContent()
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name")
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