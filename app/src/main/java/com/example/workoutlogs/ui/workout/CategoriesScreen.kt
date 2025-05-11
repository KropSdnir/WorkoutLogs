// File: app/src/main/java/com/example/workoutlogs/ui/categories/CategoriesScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 04:24:00
// Scope: Composable screen for managing categories in WorkoutLogs app

package com.example.workoutlogs.ui.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoriesScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row {
                    IconButton(onClick = { navController.navigate("drawer") }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    Spacer(Modifier.weight(1f))
                    Text("Categories", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { /* TODO: Add category */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Category")
                    }
                }
            }
        }
    ) { padding ->
        Text(
            "Categories Screen Placeholder",
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}