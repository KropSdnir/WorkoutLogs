// File: app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:00:00
// Scope: Composable for a full calendar view with monthly cards, date selection, and smooth scrolling in WorkoutLogs app

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.workoutlogs.data.model.CalendarEntry
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Composable
fun FullCalendarView(
    workoutEntries: List<CalendarEntry>,
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val today = LocalDate.now()
    val months = (-12..12).map { today.plusMonths(it.toLong()) } // 25 months centered on current
    val dayHeaders = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    onDateSelected(today)
                    onDismiss()
                }
            ) {
                Text("Today")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(months) { month ->
                MonthCalendar(
                    month = month,
                    workoutEntries = workoutEntries,
                    dayHeaders = dayHeaders,
                    onDateSelected = onDateSelected,
                    onDismiss = onDismiss
                )
            }
        }
    }
}

@Composable
fun MonthCalendar(
    month: LocalDate,
    workoutEntries: List<CalendarEntry>,
    dayHeaders: List<String>,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val firstDayOfMonth = month.with(TemporalAdjusters.firstDayOfMonth())
    val lastDayOfMonth = month.with(TemporalAdjusters.lastDayOfMonth())
    val firstDayOffset = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0
    val daysInMonth = lastDayOfMonth.dayOfMonth
    val totalCells = (daysInMonth + firstDayOffset + 6) / 7 * 7 // Round up to full weeks
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = month.format(formatter),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
            // Day Headers
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                dayHeaders.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            // Calendar Grid (8 rows max: 1 header + up to 7 weeks)
            Column {
                for (week in 0 until (totalCells / 7)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (day in 0..6) {
                            val cellIndex = week * 7 + day
                            val dayNumber = cellIndex - firstDayOffset + 1
                            val date = if (dayNumber in 1..daysInMonth) {
                                firstDayOfMonth.plusDays((dayNumber - 1).toLong())
                            } else {
                                null
                            }
                            val hasWorkout = date?.let { d ->
                                workoutEntries.any { it.date == d }
                            } ?: false
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(2.dp)
                                    .background(
                                        if (hasWorkout) MaterialTheme.colorScheme.primaryContainer
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .clickable(enabled = date != null) {
                                        date?.let {
                                            onDateSelected(it)
                                            onDismiss()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                date?.let {
                                    Text(
                                        text = dayNumber.toString(),
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center
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