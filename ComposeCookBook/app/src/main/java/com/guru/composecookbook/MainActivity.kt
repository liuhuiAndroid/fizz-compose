package com.guru.composecookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.ads.MobileAds
import com.guru.composecookbook.theme.*
import com.guru.composecookbook.ui.Animations.AnimationScreen
import com.guru.composecookbook.ui.demoui.DemoUIList
import com.guru.composecookbook.ui.home.HomeScreen
import com.guru.composecookbook.ui.learnwidgets.WidgetScreen
import com.guru.composecookbook.ui.profile.ProfileScreen
import com.guru.composecookbook.ui.utils.RotateIcon

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //for adView demo
        MobileAds.initialize(this)
        setContent {
            // SystemUiController: 一个帮助类，用于设置窗口的导航和状态栏颜色，根据API级别优雅地降级
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                MainAppContent(appTheme)
            }
        }
    }
}

@Composable
fun BaseView(
    appThemeState: AppThemeState,
    systemUiController: SystemUiController?,
    content: @Composable () -> Unit
) {
    val color = when (appThemeState.pallet) {
        ColorPallet.GREEN -> green700
        ColorPallet.BLUE -> blue700
        ColorPallet.ORANGE -> orange700
        ColorPallet.PURPLE -> purple700
    }
    systemUiController?.setStatusBarColor(color = color, darkIcons = appThemeState.darkTheme)
    ComposeCookBookTheme(darkTheme = appThemeState.darkTheme, colorPallet = appThemeState.pallet) {
        content()
    }
}

@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        // Crossfade 允许使用 crossfade 动画在两种布局之间切换。
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(appThemeState)
                    BottomNavType.WIDGETS -> WidgetScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMOUI -> DemoUIList()
                    BottomNavType.PROFILE -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    val homeScreenState = savedInstanceState { BottomNavType.HOME }
    val bottomNavBarContentDescription = stringResource(id = R.string.a11y_bottom_navigation_bar)

    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            appThemeState = appThemeState,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationContent(
            modifier = Modifier.semantics {
                accessibilityLabel = bottomNavBarContentDescription
            },
            homeScreenState = homeScreenState
        )
    }
}

@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Home) },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_home)) },
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.List) },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_widgets)) }
        )
        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 360f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_animation)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Dashboard) },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_demoui)) }
        )
        BottomNavigationItem(
            icon = { Icon(asset = Icons.Outlined.Person) },
            selected = homeScreenState.value == BottomNavType.PROFILE,
            onClick = {
                homeScreenState.value = BottomNavType.PROFILE
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.navigation_item_profile)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    BaseView(appThemeState.value, null) {
        MainAppContent(appThemeState)
    }
}