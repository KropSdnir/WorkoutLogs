package com.example.workoutlogs.ui.home

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
            text = "WorkoutLogs Menu",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        NavigationDrawerItem(
            label = { Text("Home") },
            selected = false,
            onClick = { onItemClick("home") }
        )
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            onClick = { onItemClick("settings") }
        )
    }
}