package com.example.workoutlogs.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavBar(navController: NavController) {
    val items = listOf("home", "settings")
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when (item) {
                            "home" -> Icons.Default.Home
                            "settings" -> Icons.Default.Settings
                            else -> Icons.Default.Info
                        },
                        contentDescription = item
                    )
                },
                label = { Text(item.replaceFirstChar { it.uppercase() }) },
                selected = currentDestination == item,
                onClick = {
                    navController.navigate(item) {
                        popUpTo(navController.graph.startDestinationId) {
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