package com.fizz.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.fizz.compose.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), alignment = Alignment.Center) {
        // Adds composition consistency. Use the value when LaunchedEffect is first called
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(asset = vectorResource(id = R.drawable.ic_launcher_background))
        Image(asset = vectorResource(id = R.drawable.ic_launcher_foreground))
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    LandingScreen(
        modifier = Modifier,
        onTimeout = {  }
    )
}