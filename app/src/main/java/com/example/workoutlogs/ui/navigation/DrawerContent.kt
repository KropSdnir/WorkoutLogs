// File: app/src/main/java/com/example/workoutlogs/ui/navigation/DrawerContent.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Slide-out navigation menu with Home, Workout, Exercises, and Settings options

package com.example.workoutlogs.ui.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    navController: NavHostController
) {
    val drawerState = androidx.compose.material3.rememberDrawerState(
        initialValue = if (isVisible) DrawerValue.Open else DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = navController.currentDestination?.route == "home",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("home")
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Workout") },
                    selected = navController.currentDestination?.route == "workout",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("workout")
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Exercises") },
                    selected = navController.currentDestination?.route == "exercises",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("exercises")
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = navController.currentDestination?.route == "settings",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("settings")
                    }
                )
            }
        },
        content = { /* Empty content as handled by HomeScreen */ }
    )

    if (!isVisible && drawerState.isOpen) {
        scope.launch { drawerState.close() }
    } else if (isVisible && drawerState.isClosed) {
        scope.launch { drawerState.open() }
    }
}