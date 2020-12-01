package com.example.compose.jetchat.test

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.compose.jetchat.theme.JetchatTheme

@Composable
fun TestScreen() {

}

@Preview
@Composable
fun ConvPreview480MeDefault() {
    JetchatTheme {
        TestScreen()
    }
}