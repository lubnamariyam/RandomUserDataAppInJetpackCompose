package com.lubnamariyam.soho.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.Result
import com.lubnamariyam.soho.ui.theme.SohoTheme
import org.intellij.lang.annotations.JdkConstants

@ExperimentalFoundationApi
@Composable
fun ProductListScreen(results: List<Result>,navController: NavController) {

    Column() {
        TopAppBar(
            title = {
                Row (){
                    Text(
                        text = "Soho",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif, color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    WeatherState()
                }
            },
        )
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {


        }

        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(results.size) { index ->
                ProfileCard(result = results[index],navController)
            }
        }



    }
}

@Composable
fun ProfileCard(result: Result,navController: NavController) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(220.dp)
            .background(Color.White).clickable{navController.navigate("profile_description") }, shape = RoundedCornerShape(8.dp), elevation = 6.dp,
    ) {
        Surface(
            modifier = Modifier.background(Color.White)
        ) {
            Column(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = result.picture.large,
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
                        .weight(0.2f)
                )
                Text(
                    text = "${result.name.first}  ${result.name.last}",
                    modifier = Modifier.padding(start = 4.dp),
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.padding(4.dp))

            }


        }
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

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                /*.onFocusChanged {
                    isHintDisplayed = it.isFocused != it.hasFocus
                }*/
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun WeatherState(){
    Row() {
        Column() {
            Text(text = "31  Coimbatore",textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
            Text(text = "Scattered Clouds",textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp)
        }
        Image(painter = painterResource(id = com.lubnamariyam.soho.R.drawable.rainy), contentDescription = "sun" , modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Preview(name = "profile")
@Composable
private fun FeaturedCoursePreview() {
    SohoTheme {
    }

}

