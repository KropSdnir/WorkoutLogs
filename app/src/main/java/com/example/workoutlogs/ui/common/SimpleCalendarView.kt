// File: app/src/main/java/com/example/workoutlogs/ui/common/SimpleCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:00:00
// Scope: Composable for a single date display with tap-to-expand full calendar in WorkoutLogs app

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.workoutlogs.ui.home.HomeViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SimpleCalendarView(viewModel: HomeViewModel) {
    var showFullCalendar by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val workoutEntries by viewModel.workoutEntries.collectAsState()
    val today = LocalDate.now()
    val isToday = selectedDate == today
    val hasWorkout = workoutEntries.any { it.date == selectedDate }

    if (showFullCalendar) {
        FullCalendarView(
            workoutEntries = workoutEntries,
            onDismiss = { showFullCalendar = false },
            onDateSelected = { date ->
                selectedDate = date
                showFullCalendar = false
            }
        )
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { showFullCalendar = true },
            colors = CardDefaults.cardColors(
                containerColor = if (hasWorkout) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isToday) "Today" else selectedDate.format(
                        DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}