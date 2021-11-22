package com.example.randomfood.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.randomfood.routing.Router
import com.example.randomfood.routing.Screen

@Composable
fun TopAppBarCustom() {
    TopAppBar(
        title = {
            Icon(Icons.Filled.Star, contentDescription = "Random Food", tint = Color.White)
            Text(text = "Hôm nay ăn gì?", color = Color.White)
        },

        backgroundColor = Color(0xFF039CE4),
        elevation = AppBarDefaults.TopAppBarElevation
    )
}