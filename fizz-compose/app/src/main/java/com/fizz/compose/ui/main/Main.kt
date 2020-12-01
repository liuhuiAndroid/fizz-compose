package com.fizz.compose.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import com.fizz.compose.R
import com.fizz.compose.ui.theme.BlueTheme

@Composable
fun Main() {
    BlueTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            bottomBar = {

            }
        ) {

        }
    }
}

private enum class MainTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    HOME(R.string.main_home, R.drawable.ic_grain),
    FIND_HOUSE(R.string.main_find_house, R.drawable.ic_featured),
    RECOMMEND(R.string.main_recommend, R.drawable.ic_search),
    MESSAGE(R.string.main_message, R.drawable.ic_search),
    PROFILE(R.string.main_profile, R.drawable.ic_search)
}
