package com.catching.pucks.database.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catching.pucks.database.DataBase.User
import com.catching.pucks.database.ui.theme.Pink40
import com.catching.pucks.database.ui.theme.Purple80
import com.catching.pucks.database.ui.theme.PurpleGrey80

@Composable
fun UserCard(user: User, onClickDelete: (User) -> Unit, onClickUpdate: (User) -> Unit) {
    val width = LocalConfiguration.current.screenWidthDp

    val openDialogDelete = remember { mutableStateOf(false) }
    val openDialogCreate = remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .size((width * 1).dp, (width * 0.55).dp)
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Box(modifier = Modifier,
                    contentAlignment = Alignment.CenterStart
                    ) {
                    Text(
                        user.name,
                        fontSize = 18.sp,
                        color = Color.Black
                        )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { openDialogCreate.value = true },
                        modifier = Modifier.size((width * 0.07).dp),
                    ) {
                        Icon(
                            Icons.Filled.Edit, contentDescription = "",
                            tint = PurpleGrey80
                        )
                    }

                    Spacer(modifier = Modifier.size((width * 0.03).dp))

                    IconButton(
                        onClick = { openDialogDelete.value = true },
                        modifier = Modifier.size((width * 0.07).dp),
                    ) {
                        Icon(
                            Icons.Filled.Delete, contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            tint = Purple80
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size((width * 0.04).dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
                ) {
                Box(modifier = Modifier
                    .size((width * 0.27).dp, (width * 0.12).dp)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxSize()
                        ) {

                        Text(
                            text = user.tags,
                            fontSize = 14.sp,
                            color = Color.Black,

                            )
                    }
                }

                Spacer(modifier = Modifier.size((8.dp)))
                
                Box(modifier = Modifier
                    .size((width * 0.27).dp, (width * 0.12).dp)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxSize()
                        ) {
                        Text(text = user.tags2,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.size((8.dp)))

                Box(modifier = Modifier
                    .clickable { }
                    .size((width * 0.27).dp, (width * 0.12).dp)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clipToBounds(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxSize()
                        ) {
                        Text(text = user.tags3,
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.size((width * 0.04).dp))

            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
                ) {
                Row(modifier = Modifier
                    .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    Text(text = "На складе",
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                        )

                    Text(text = "Дата обновления",
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth(0.75f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = user.amount.toString(),
                        fontSize = 15.sp,
                        color = Color.Black,

                    )

                    Text(text = user.time,
                        fontSize = 15.sp,
                        color = Color.Black,

                    )
                }
            }
        }

        if (openDialogDelete.value) {
            AlertDialog(
                onDismissRequest = { openDialogDelete.value = false},
                title = {

                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Icon(Icons.Filled.Warning, contentDescription = "", tint = Color.Gray)

                        Spacer(modifier = Modifier.size((width * 0.05).dp))

                        Text(text = "Удаление товара",
                            fontSize = 24.sp,
                            color = Color.Black
                            )
                    }
                        },
                text = { Text("Вы действительно хотите удалить товар?",
                    fontSize = 15.sp,
                    color = Color.Black
                    ) },
                confirmButton = {

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                        ) {
                        IconButton({ openDialogDelete.value = false }) {
                            Text("Нет", fontSize = 15.sp, color = Pink40)
                        }

                        Spacer(modifier = Modifier.size((width * 0.05).dp))

                        IconButton({ openDialogDelete.value = false
                                onClickDelete(user)
                        }) {
                            Text("Да", fontSize = 15.sp, color = Pink40)
                        }
                    }


                },
                containerColor = Color.White
            )
        }


        if (openDialogCreate.value) {
            var temp = remember {
                mutableStateOf(user.amount)
            }

            AlertDialog(
                onDismissRequest = { openDialogCreate.value = false},
                title = {

                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = "", tint = Color.Gray)

                        Spacer(modifier = Modifier.size((width * 0.05).dp))

                        Text(text = "Количество товара",
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                    }
                },
                text = {

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                        IconButton(onClick = { temp.value-- }) {
                            Text(text = "-",
                                fontSize = 25.sp,
                                color = Pink40
                                )
                        }

                        Text(temp.value.toString(),
                            fontSize = 25.sp,
                            color = Color.Black
                        )

                        IconButton(onClick = { temp.value++ }) {
                            Text(text = "+",
                                fontSize = 25.sp,
                                color = Pink40
                            )
                        }
                    }
                       },
                confirmButton = {

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton({ openDialogCreate.value = false },
                            modifier = Modifier.size((width * 0.2).dp, (width * 0.1).dp)
                            ) {
                            Text("Отмена", fontSize = 15.sp, color = Pink40)
                        }

                        Spacer(modifier = Modifier.size((width * 0.05).dp))

                        IconButton({ openDialogCreate.value = false
                            user.amount = temp.value
                            onClickUpdate(user)
                        },
                            modifier = Modifier.size((width * 0.2).dp, (width * 0.1).dp)
                            ) {
                            Text("Принять", fontSize = 15.sp, color = Pink40)
                        }
                    }


                },
                containerColor = Color.White
            )
        }
    }
}