package com.example.randomfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.randomfood.routing.Router
import com.example.randomfood.routing.Screen
import com.example.randomfood.ui.screens.AddFoodScreen
import com.example.randomfood.ui.screens.ListFoodScreen
import com.example.randomfood.ui.screens.RandomFoodScreen
import com.example.randomfood.ui.theme.RandomFoodTheme
import com.example.randomfood.viewmodel.MainViewModel
import com.example.randomfood.viewmodel.MainViewModelFactory

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as RandomFoodApplication).dependencyInjector.repository
        )
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RandomFoodTheme {
                Surface(color = MaterialTheme.colors.background) {
                    when (Router.currentScreen) {
                        is Screen.RandomFood -> RandomFoodScreen(viewModel)
                        is Screen.ListFood -> ListFoodScreen(viewModel)
                        is Screen.AddFood -> AddFoodScreen()
                    }
                }
            }
        }
    }
}
