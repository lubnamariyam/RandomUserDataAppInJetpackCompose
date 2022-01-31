package com.lubnamariyam.soho.ui.view.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.randomuser.RandomUserResponse
import com.lubnamariyam.soho.model.randomuser.Result
import com.lubnamariyam.soho.ui.view.ProfileScreen.Companion.resultData
import com.lubnamariyam.soho.ui.view.SearchScreen.Companion.randomUserResponseData


@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun ListContent(items: LazyPagingItems<RandomUserResponse> , navController: NavController) {
    Log.d("Error", items.loadState.toString())
    LazyColumn {
        items(
            items = items,
            key = { randomUser ->
                randomUser.id
            }
        ) { randomUser ->
            randomUser?.let {
                randomUserResponseData = randomUser
                for (data in it.results)
                    MessageCard(result = data,navController)
            }
        }
    }
}

@Composable
fun MessageCard(result: Result,navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 20.dp).clickable {
        resultData = result
        navController.navigate("profile_description")
    }) {
        Image(
            painter = rememberImagePainter(
                data = result.picture.large,
                builder = {
                    scale(Scale.FIT)
                    placeholder(coil.compose.base.R.drawable.notification_action_background)
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
