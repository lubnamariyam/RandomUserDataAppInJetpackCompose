package com.lubnamariyam.soho.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.Result
import com.lubnamariyam.soho.model.weather.WeatherResponse
import com.lubnamariyam.soho.ui.theme.LightGrey
import com.lubnamariyam.soho.ui.view.ProfileScreen.Companion.resultData
import com.lubnamariyam.soho.viewModel.HomeViewModel

@Composable
fun Profile(navController: NavController , homeViewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopAppBar(
            title = {
                Row (){
                    Icon(
                        Icons.Default.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(24.dp)
                            .clickable {
                                navController.navigate("home_screen")
                            }
                    )
                    Spacer(Modifier.weight(1f))
                    WeatherState(homeViewModel)
                }
            },
        )

        Card(modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth()
            .background(Color.White),
            shape = RoundedCornerShape(8.dp), elevation = 6.dp,) {
            Surface(
                modifier = Modifier.background(Color.White)
            ){
                Column() {
                    PictureCard()
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Name : ${resultData.name.first} ${resultData.name.last}",
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Email : ${resultData.email} ",
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Gender : ${resultData.gender} ",
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Phone : ${resultData.phone} ",
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Cell : ${resultData.cell} ",
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.padding(4.dp))

                    }
                }
            }



        }
    }
}

class ProfileScreen {
    companion object {
        lateinit var resultData: Result
    }
}

@Composable
fun PictureCard() {
    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = resultData.picture.large,
                builder = {
                    scale(Scale.FIT)
                    placeholder(R.drawable.notification_action_background)
                    transformations()
                }
            ),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
}

@Composable
fun WeatherState(homeViewModel: HomeViewModel){
    var response = homeViewModel.weatherResponse
    if (response.name.isNotEmpty()){
        var temp = response.main.temp - 273.15
        Row() {
            Column() {
                Text(text = "${temp.toInt()} °C ${response.name}",textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
                Text(text = response.weather[0].description,textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
            }
            Image(painter = painterResource(id = com.lubnamariyam.soho.R.drawable.rainy), contentDescription = "sun" , modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }else{
        Row() {
            Column() {
                Text(text = "18°C UK",textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
                Text(text = "cloudy",textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Image(painter = painterResource(id = com.lubnamariyam.soho.R.drawable.rainy), contentDescription = "sun" , modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

}




