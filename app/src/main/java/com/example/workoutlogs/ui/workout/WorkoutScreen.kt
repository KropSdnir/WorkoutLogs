// File: WorkoutLogs/app/src/main/java/com/workoutlogs/ui/common/NavBar.kt
// Timestamp: Updated on 2025-05-09 17:00:00
// Scope: Composable for top navigation bar with menu, calendar, and add actions
package com.workoutlogs.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun NavBar(
    navController: NavHostController,
    onMenuClick: () -> Unit,
    onCalendarClick: () -> Unit
) {
    var showAddOptions by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text("WorkoutLogs") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { onMenuClick() }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = onCalendarClick) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Calendar")
            }
            IconButton(onClick = { showAddOptions = !showAddOptions }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
            DropdownMenu(
                expanded = showAddOptions,
                onDismissRequest = { showAddOptions = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Weight") },
                    onClick = {
                        navController.navigate("exercise_details")
                        showAddOptions = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Cardio") },
                    onClick = {
                        navController.navigate("cardio_detail")
                        showAddOptions = false
                    }
                )
            }
        }
    )
}