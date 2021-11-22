package com.example.randomfood.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.randomfood.routing.Screen

data class ScreenData(
    val route: String,
    val screen: Screen,
    val text: String,
    val icon: ImageVector,
    var isSelected: Boolean
)

public val screens = listOf(
    ScreenData("RandomFood",Screen.RandomFood, "Hôm nay ăn gì?", Icons.Filled.Star, true),
    ScreenData("ListFood",Screen.ListFood, "Danh sách món ăn", Icons.Filled.List, false)
)