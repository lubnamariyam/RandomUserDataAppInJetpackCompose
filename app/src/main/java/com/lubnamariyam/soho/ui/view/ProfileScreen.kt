package com.lubnamariyam.soho.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale


@Composable
fun Profile() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
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
                Text(
                    text = "",
                    modifier = Modifier.padding(start = 4.dp),
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }

}

@Composable
fun PictureCard() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.White),
        shape = RoundedCornerShape(8.dp), elevation = 6.dp,
    ) {
        Surface(
            modifier = Modifier.background(Color.White)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "",
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
}