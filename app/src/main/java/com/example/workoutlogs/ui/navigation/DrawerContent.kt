// File: WorkoutLogs/app/src/main/java/com/workoutlogs/ui/navigation/DrawerContent.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Composable for navigation drawer content
package com.workoutlogs.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController) {
    ModalDrawerSheet {
        Text("Menu", modifier = Modifier.padding(16.dp))
        Divider()
        NavigationDrawerItem(
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            onClick = { navController.navigate("settings") }
        )
    }
}