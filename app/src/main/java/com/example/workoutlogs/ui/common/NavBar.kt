// File: app/src/main/java/com/example/workoutlogs/ui/common/NavBar.kt
// Timestamp: Updated on 2025-05-09 14:00:00
// Scope: Top navigation bar with Menu icon (left), title, and Calendar/Plus icons (right)

package com.example.workoutlogs.ui.common

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.example.workoutlogs.R

@Composable
fun NavBar(
    onMenuClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onNavigateToExerciseDetail: () -> Unit,
    onNavigateToCardioDetail: () -> Unit
) {
    val showPlusOptions = remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Home", style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = onCalendarClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar"
                )
            }
            IconButton(onClick = { showPlusOptions.value = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add"
                )
            }
            DropdownMenu(
                expanded = showPlusOptions.value,
                onDismissRequest = { showPlusOptions.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Weight") },
                    onClick = {
                        showPlusOptions.value = false
                        onNavigateToExerciseDetail()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Cardio") },
                    onClick = {
                        showPlusOptions.value = false
                        onNavigateToCardioDetail()
                    }
                )
            }
        }
    )
}