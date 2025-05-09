// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 07:32:00
// Scope: Composable for the workout screen with tab navigation and Room data in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutlogs.data.model.CalendarEntry
import com.example.workoutlogs.ui.common.SimpleCalendarView
import com.example.workoutlogs.ui.home.HomeViewModel
import com.example.workoutlogs.ui.navigation.DrawerContent
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WorkoutScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableStateOf(0) }
    val workoutEntries by viewModel.workoutEntries.collectAsState()
    var showMenu by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                onItemClick = { route: String ->
                    scope.launch {
                        drawerState.close()
                        navController.navigate(route)
                    }
                }
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Workout") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("home") }) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                Box {
                    FloatingActionButton(
                        onClick = { showMenu = true }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Workout")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Exercises") },
                            onClick = {
                                navController.navigate("workout_exercises")
                                showMenu = false
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Tab Row for Home, Statistics, History
                TabRow(selectedTabIndex = selectedTab) {
                    listOf("Home", "Statistics", "History").forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index }
                        )
                    }
                }

                // Tab Content
                when (selectedTab) {
                    0 -> {
                        // Home Tab: Show Calendar
                        SimpleCalendarView(viewModel = viewModel)
                    }
                    1 -> {
                        // Statistics Tab: Simple Bar Chart
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Workout Frequency (Mock Data)",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            // Mock data: workouts per day of week
                            val workoutCounts = listOf(3, 5, 2, 4, 6, 1, 0)
                            val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                workoutCounts.forEachIndexed { index, count ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .width(30.dp)
                                                .height((count * 20).dp)
                                                .background(MaterialTheme.colorScheme.primary)
                                        )
                                        Text(
                                            text = days[index],
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        // History Tab: List of Workouts from Room
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Workout History",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            LazyColumn {
                                items(workoutEntries) { entry ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                    ) {
                                        Text(
                                            text = "${entry.date}: ${entry.workoutName} - ${entry.sets} sets",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}