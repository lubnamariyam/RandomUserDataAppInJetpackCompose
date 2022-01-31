package com.lubnamariyam.soho.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.randomuser.Result
import com.lubnamariyam.soho.ui.theme.LightGrey
import com.lubnamariyam.soho.ui.view.ProfileScreen.Companion.resultData

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey),
    ) {
        PictureCard()

        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(8.dp), elevation = 6.dp,
        ) {
            Surface(
                modifier = Modifier.background(Color.White)
            ) {
                Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly) {
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

class ProfileScreen {
    companion object {
        lateinit var resultData: Result
    }
}

@Composable
fun PictureCard() {

    Column(verticalArrangement = Arrangement.Top, modifier = Modifier
        .fillMaxWidth()
        .height(360.dp)) {
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
