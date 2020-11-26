package com.github.skydoves.landscapistdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.github.skydoves.landscapistdemo.R
import com.github.skydoves.landscapistdemo.model.MockUtil
import com.github.skydoves.landscapistdemo.theme.DisneyComposeTheme
import com.github.skydoves.landscapistdemo.theme.purple200

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyComposeTheme {
                Scaffold(
                        backgroundColor = MaterialTheme.colors.primarySurface,
                        topBar = { PosterAppBar() }
                ) {
                    DisneyPosters(posters = MockUtil.getMockPosters())
                }
            }
        }
    }

    @Composable
    fun PosterAppBar() {
        TopAppBar(
                elevation = 6.dp,
                backgroundColor = purple200,
                modifier = Modifier.preferredHeight(58.dp)
        ) {
            Text(
                    modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically),
                    text = stringResource(R.string.app_name),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
            )
        }
    }

    @Preview
    @Composable
    fun MainActivityPreview() {
        DisneyComposeTheme {
            Scaffold(
                    backgroundColor = MaterialTheme.colors.primarySurface,
                    topBar = { PosterAppBar() }
            ) {
                DisneyPosters(posters = MockUtil.getMockPosters())
            }
        }
    }
}

