package com.example.randomfood.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.randomfood.domain.model.screens
import com.example.randomfood.routing.Router


@Composable
fun CostBottomNavigation() {
    val navController = rememberNavController()

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomNavigationItem(
            icon = {
                Icon(screens[0].icon, "${screens[0].route} Icon")
            },
            label = { Text(text = screens[0].text) },
            selected = currentDestination?.hierarchy?.any { it.route == screens[0].route } == true,
            onClick = {
                Router.navigateTo(screens[0].screen)
                screens[0].isSelected = true
                screens[1].isSelected = false
            },
            alwaysShowLabel = screens[0].isSelected,
        )

        BottomNavigationItem(
            icon = {
                Icon(screens[1].icon, "${screens[1].route} Icon")
            },
            label = { Text(text = screens[1].text) },
            selected = currentDestination?.hierarchy?.any { it.route == screens[1].route } == true,
            onClick = {
                Router.navigateTo(screens[1].screen)
                screens[0].isSelected = false
                screens[1].isSelected = true
            },
            alwaysShowLabel = screens[1].isSelected,
        )

    }
}

@Composable
fun BottomAppBarCustom() {


    BottomAppBar(content = {
        CostBottomNavigation()
    })
}