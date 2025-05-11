// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-10 00:23:00
// Scope: Composable screen for home in WorkoutLogs app

package com.example.workoutlogs.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer content", modifier = Modifier.padding(16.dp))
                // Add drawer items here
            }
        },
        gesturesEnabled = false // Disable swipe-right
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { navController.navigate("drawer") }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    Spacer(Modifier.weight(1f))
                    Text("Home", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = { navController.navigate("workout_exercises") },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Workout")
                    }
                }
            }
        ) { padding ->
            Text(
                "Home Screen Placeholder",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
fun Spacer(x0: Modifier) {
    TODO("Not yet implemented")
}