// File: app/src/main/java/com/example/workoutlogs/ui/navigation/DrawerContent.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 07:32:00
// Scope: Composable for the sliding navigation drawer in WorkoutLogs app

package com.example.workoutlogs.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        NavigationDrawerItem(
            label = { Text("Home") },
            selected = false,
            onClick = { onItemClick("home") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            onClick = { onItemClick("settings") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}