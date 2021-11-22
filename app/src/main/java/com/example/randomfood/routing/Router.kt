package com.example.randomfood.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Class defining all possible screens in the app.
 */
sealed class Screen {
    object ListFood : Screen()
    object AddFood : Screen()
    object RandomFood : Screen()
}

/**
 * Allows you to change the screen in the [MainActivity]
 *
 * Also keeps track of the current screen.
 */
object Router {
    var currentScreen: Screen by mutableStateOf(Screen.RandomFood)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}
