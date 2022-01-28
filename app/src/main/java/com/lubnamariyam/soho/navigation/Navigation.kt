package com.lubnamariyam.soho.navigation

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lubnamariyam.soho.model.Result
import com.lubnamariyam.soho.ui.theme.Splash
import com.lubnamariyam.soho.ui.view.ProductListScreen
import com.lubnamariyam.soho.ui.view.Profile
import com.lubnamariyam.soho.viewModel.HomeViewModel

@ExperimentalFoundationApi
@Composable
fun Navigation(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            Splash(navController )
        }
        // Products screen
        composable("home_screen") {
            val activity = (LocalContext.current as? Activity)
            ProductListScreen(homeViewModel.productResponse.results,navController)
        }
        composable("profile_description") {
            Profile()
        }
    }

}