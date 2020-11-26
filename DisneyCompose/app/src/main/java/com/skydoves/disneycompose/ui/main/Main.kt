package com.skydoves.disneycompose.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.skydoves.disneycompose.ui.details.PosterDetails
import com.skydoves.disneycompose.ui.posters.Posters
import com.skydoves.disneycompose.utils.ProvideDisplayInsets
import com.skydoves.landscapist.coil.CoilImageLoaderAmbient

@Composable
fun DisneyMain(viewModel: MainViewModel) {
    val navController = rememberNavController()

    ProvideDisplayInsets {
        Providers(CoilImageLoaderAmbient provides viewModel.imageLoader) {
            NavHost(navController = navController, startDestination = NavScreen.Home.route) {
                composable(NavScreen.Home.route) {
                    Posters(
                            viewModel = viewModel,
                            selectPoster = {
                                navController.navigate("${NavScreen.PosterDetails.route}/$it")
                            }
                    )
                }
                composable(
                        route = NavScreen.PosterDetails.routeWithArgument,
                        arguments = listOf(
                                navArgument(NavScreen.PosterDetails.argument0) { type = NavType.LongType }
                        )
                ) {
                    val posterId =
                            it.arguments?.getLong(NavScreen.PosterDetails.argument0)
                                    ?: return@composable

                    viewModel.getPoster(posterId)

                    PosterDetails(viewModel = viewModel) {
                        navController.popBackStack(navController.graph.startDestination, false)
                    }
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object PosterDetails : NavScreen("PosterDetails") {

        const val routeWithArgument: String = "PosterDetails/{posterId}"

        const val argument0: String = "posterId"
    }
}
