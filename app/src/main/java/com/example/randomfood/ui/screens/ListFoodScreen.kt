package com.example.randomfood.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomfood.domain.model.FoodModel
import com.example.randomfood.routing.Router
import com.example.randomfood.routing.Screen
import com.example.randomfood.ui.components.BottomAppBarCustom
import com.example.randomfood.ui.components.Food
import com.example.randomfood.ui.components.TopAppBarCustom
import com.example.randomfood.viewmodel.MainViewModel

@Composable
fun ListFoodScreen(
    viewModel: MainViewModel
) {
    val foods: List<FoodModel> by viewModel.allFoods.observeAsState(listOf())
    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 150.dp)
            ) {
                items(items = foods, itemContent = { item ->
                    Food(food = item)
                })
            }
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Router.navigateTo(Screen.AddFood)
                },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add a new food"
                    )
                }
            )
        },

        bottomBar = {
            BottomAppBarCustom()
        }
    )
}