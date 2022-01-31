package com.lubnamariyam.soho.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.RandomUserResponse
import com.lubnamariyam.soho.model.Result
import com.lubnamariyam.soho.ui.theme.LightGrey
import com.lubnamariyam.soho.ui.theme.Peach200
import com.lubnamariyam.soho.ui.theme.Peach500
import com.lubnamariyam.soho.ui.view.SearchScreen.Companion.randomUserResponseData
import java.util.*
import kotlin.collections.ArrayList


@Composable
fun SearchScreenUi(navController: NavController){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column() {
        Search(state = textState)
        ProfileList(navController = navController, state = textState)
    }
}
@Composable
fun ProfileList(navController: NavController, state: MutableState<TextFieldValue>) {
    var persons = randomUserResponseData
    var filteredPerson: ArrayList<com.lubnamariyam.soho.model.Result> = arrayListOf()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        if(searchedText.isNotEmpty()){
            val resultList = ArrayList<com.lubnamariyam.soho.model.Result>()
            for (person in persons) {
                if (person.name.first.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(person)
                }
            }
            filteredPerson = resultList
        }

        items(filteredPerson.size) { filteredData ->

            MessageCard(
                result = filteredPerson[filteredData],
                onItemClick = { selectedText ->
                    ProfileScreen.resultData = filteredPerson[filteredData]
                    navController.navigate("profile_description") {
                        popUpTo("home_screen") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Search(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Green,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = LightGrey,
            focusedIndicatorColor = Peach500,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun MessageCard(result: Result, onItemClick: (Result) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 20.dp).clickable { onItemClick(result) }) {
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
                .size(56.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(
                text = "${result.name.first} ${result.name.last}",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = result.email,
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
        }

    }
}

class SearchScreen{
    companion object{
        lateinit var randomUserResponseData : List<Result>
    }
}


@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Search(textState)
}