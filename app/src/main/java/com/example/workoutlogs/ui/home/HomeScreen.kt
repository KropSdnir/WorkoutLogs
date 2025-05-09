// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Main screen displaying NavBar, SimpleCalendarView, SlideOutMenu (DrawerContent), and Statistics/History tabs

package com.example.workoutlogs.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.workoutlogs.ui.common.NavBar
import com.example.workoutlogs.ui.common.SimpleCalendarView
import com.example.workoutlogs.ui.navigation.DrawerContent

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val showCalendar = remember { mutableStateOf(false) }
    val showMenu = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            NavBar(
                onMenuClick = { showMenu.value = true },
                onCalendarClick = { showCalendar.value = !showCalendar.value },
                onNavigateToExerciseDetail = { navController.navigate("exercise_detail") },
                onNavigateToCardioDetail = { navController.navigate("cardio_detail") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            if (showCalendar.value) {
                SimpleCalendarView()
            }
            DrawerContent(
                isVisible = showMenu.value,
                onDismiss = { showMenu.value = false },
                navController = navController
            )
            TabRow(selectedTabIndex = 0) {
                Tab(
                    selected = true,
                    onClick = { /* Statistics placeholder */ },
                    text = { Text("Statistics", style = MaterialTheme.typography.labelLarge) }
                )
                Tab(
                    selected = false,
                    onClick = { /* History placeholder */ },
                    text = { Text("History", style = MaterialTheme.typography.labelLarge) }
                )
            }
        }
    }
}