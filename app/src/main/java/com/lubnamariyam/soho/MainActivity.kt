package com.lubnamariyam.soho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.lubnamariyam.soho.navigation.Navigation
import com.lubnamariyam.soho.ui.theme.SohoTheme
import com.lubnamariyam.soho.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    val homeViewModel by viewModels<HomeViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            println("Hii--> setContent")
            SohoTheme {
                println("Hii--> sohoTheme")
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    println("Hii-->")
                    homeViewModel.getProductList()
                    /*homeViewModel.response.observe(this, Observer {
                        it.results

                    })*/
                    Navigation(homeViewModel)

                    //
                    //println("Hello--> " + homeViewModel.productResponse.results)
                }
            }
        }
    }
}


@Preview()
@Composable
fun DefaultPreview() {
    SohoTheme {

    }
}