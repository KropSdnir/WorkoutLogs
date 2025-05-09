// File: WorkoutLogs/app/src/main/java/com/workoutlogs/ui/home/HomeScreen.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Implements Home Screen with navigation bar, drawer, calendar, and tabs using MVVM
package com.workoutlogs.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.workoutlogs.ui.common.NavBar
import com.workoutlogs.ui.common.SimpleCalendarView
import com.workoutlogs.ui.navigation.DrawerContent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val tabs = listOf("Statistics", "History")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController)
        }
    ) {
        Scaffold(
            topBar = {
                NavBar(
                    navController = navController,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onCalendarClick = { viewModel.toggleCalendar() }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (viewModel.showCalendar.value) {
                    SimpleCalendarView()
                }
                TabRow(selectedTabIndex = viewModel.selectedTab.value) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = viewModel.selectedTab.value == index,
                            onClick = { viewModel.selectTab(index) }
                        )
                    }
                }
                when (viewModel.selectedTab.value) {
                    0 -> StatisticsTab()
                    1 -> HistoryTab()
                }
            }
        }
    }
}

@Composable
fun StatisticsTab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Statistics (To be implemented)")
    }
}

@Composable
fun HistoryTab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("History (To be implemented)")
    }
}