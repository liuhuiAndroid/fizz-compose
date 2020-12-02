package com.fizz.compose.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.fizz.compose.R
import com.fizz.compose.ui.components.FizzAppBar
import com.fizz.compose.ui.components.ScaleIcon
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(MainTabs.HOME) }
    val tabs = MainTabs.values()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigation(
                Modifier.navigationBarsHeight(additional = 56.dp)
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            ScaleIcon(
                                tab == selectedTab,
                                vectorResource(tab.icon),
                                1.1f,
                                800
                            )
                        },
                        label = { Text(stringResource(tab.title).toUpperCase()) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (selectedTab) {
            MainTabs.HOME -> MainHome(modifier)
            MainTabs.FIND_HOUSE -> MainFindHouse(modifier)
            MainTabs.RECOMMEND -> MainRecommend(modifier)
            MainTabs.MESSAGE -> MainMessage(modifier)
            MainTabs.PROFILE -> MainProfile(modifier)
        }
    }
}

private enum class MainTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    HOME(R.string.main_home, R.drawable.ic_main_home),
    FIND_HOUSE(R.string.main_find_house, R.drawable.ic_main_find_house),
    RECOMMEND(R.string.main_recommend, R.drawable.ic_main_recommend),
    MESSAGE(R.string.main_message, R.drawable.ic_main_message),
    PROFILE(R.string.main_profile, R.drawable.ic_main_profile)
}
