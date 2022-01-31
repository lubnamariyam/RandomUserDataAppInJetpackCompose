package com.lubnamariyam.soho

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lubnamariyam.soho.navigation.Navigation
import com.lubnamariyam.soho.ui.theme.SohoTheme
import com.lubnamariyam.soho.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    val homeViewModel by viewModels<HomeViewModel>()
    var REQUESTCODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            SohoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    homeViewModel.getProductList()
                    checkUserPermissionAlert(homeViewModel)
                    Navigation(homeViewModel)
                }
            }
        }

    }
    fun checkUserPermissionAlert(homeViewModel: HomeViewModel) {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            try {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUESTCODE
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location?.latitude != null) {
                        homeViewModel.getWeatherData(location.latitude, location.longitude)
                    }
                }
                .addOnFailureListener {
                    // Failure Case
                }
        }
    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUESTCODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            if (location?.latitude != null) {
                                homeViewModel.getWeatherData(location.latitude, location.longitude)
                            }
                        }
                        .addOnFailureListener {
                            // Failure Case
                        }
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        REQUESTCODE
                    )
                }
                return
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