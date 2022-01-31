package com.lubnamariyam.soho.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.lubnamariyam.soho.ui.theme.Splash
import com.lubnamariyam.soho.ui.view.home.HomeScreenNew
import com.lubnamariyam.soho.ui.view.Profile
import com.lubnamariyam.soho.ui.view.SearchScreenUi

@ExperimentalCoilApi
@OptIn(ExperimentalPagingApi::class)
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            Splash(navController)
        }
        composable("home_screen") {
            HomeScreenNew(navController)
        }
        composable("search_screen") {
            SearchScreenUi(navController)
        }
        composable("profile_description") {
            Profile()
        }
    }

}