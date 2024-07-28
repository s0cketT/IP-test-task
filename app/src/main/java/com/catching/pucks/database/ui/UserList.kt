package com.catching.pucks.database.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catching.pucks.database.R
import com.catching.pucks.database.ViewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(userViewModel: UserViewModel = viewModel(factory = UserViewModel.factiry)) {

    val hiaght = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp

    val searchText by userViewModel.searchText.collectAsState()

    val users = userViewModel.list.collectAsState(initial = emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .size((hiaght * 0.15).dp)
            .background(colorResource(R.color.blue)),
            contentAlignment = Alignment.BottomCenter
            ) {
            Text(text = "Список товаров",
                fontSize = 26.sp,
                color = Color.Black,
                modifier = Modifier
                    .offset(0.dp, -(width * 0.05).dp)
                )

        }
        
        Box(modifier = Modifier
            .fillMaxWidth(0.95f)
            .size((hiaght * 0.075).dp),
            contentAlignment = Alignment.Center
            ) {
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )

                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ) {
                Box(modifier = Modifier.background(Color.White)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Search, contentDescription = "",
                            modifier = Modifier
                                .size((width * 0.07).dp),
                            tint = Color.Gray
                        )
                    }
                }
                TextField(
                    value = searchText,
                    onValueChange = {userViewModel.onSearchTextChange(it)},
                    placeholder = {
                        Text(text = "Поиск товаров")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xffeeeeee),
                        unfocusedTextColor = Color(0xff888888),
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color(0xff222222),
                        cursorColor = Color.Gray,
                        focusedSupportingTextColor = Color.Black,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray
                    )
                )
            }
        }

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .size((hiaght * 0.77).dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(users.value) { user ->
                UserCard(user, { userViewModel.deleteUser(it) }, { userViewModel.updateUser(it) })
            }
        }
    }

}
