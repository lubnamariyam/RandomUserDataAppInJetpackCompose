package com.lubnamariyam.soho.ui.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.lubnamariyam.soho.R
import com.lubnamariyam.soho.model.weather.WeatherResponse
import com.lubnamariyam.soho.ui.theme.LightGrey
import com.lubnamariyam.soho.viewModel.HomeViewModel

@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreenNew(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()

) {
    val getAllUsersData = homeViewModel.getAllUsers.collectAsLazyPagingItems()

    Column {
        TopAppBar(
            title = {
                Row {
                    Text(
                        text = "Soho",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif, color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    //WeatherState(weatherViewModel.weatherResponse)
                }
            },
        )
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RectangleShape)
                .padding(top = 10.dp, start = 8.dp, end = 8.dp)
                .clickable {
                    navController.navigate("search_screen")
                }
        ) {
        }
        ListContent(items = getAllUsersData, navController = navController)
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier
        .clip(shape = MaterialTheme.shapes.medium)
        .background(LightGrey)) {
        Row {

            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(15.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )

        }

    }
}

@Composable
fun WeatherState(weatherResponse: WeatherResponse) {
    Row {
        Column {
            val deg = weatherResponse.main.temp - 273.15
            Text(
                text = "${deg.toInt()} °C ${weatherResponse.name}", textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp
            )
            Text(
                text = weatherResponse.weather[0].description, textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.rainy),
            contentDescription = "sun",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
    }
}
