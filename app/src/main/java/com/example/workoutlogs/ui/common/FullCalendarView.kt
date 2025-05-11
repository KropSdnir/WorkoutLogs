// File: app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 20:30:00 CEST
// Scope: Month grid calendar composable for WorkoutLogs app
// Note: Replace this file at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// with this file if changes occurred. No changes since R72.
// Supports date selection and return to SimpleCalendarView.
// Sourced from https://github.com/KropSdnir/WorkoutLogs (ui/common/).
// Verify this file is applied correctly by checking the Timestamp and calendar display.
// If incorrect:
// 1. Share local FullCalendarView.kt from ui/common/.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun FullCalendarView(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val currentMonth = remember { mutableStateOf(selectedDate.withDayOfMonth(1)) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display 3 months (previous, current, next) for scrolling
        items(3) { index ->
            val monthDate = currentMonth.value.plusMonths((index - 1).toLong())
            MonthGrid(monthDate, selectedDate, onDateSelected)
        }
    }
}

@Composable
fun MonthGrid(
    date: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = date.withDayOfMonth(1)
    val daysInMonth = date.lengthOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0

    Column {
        Text(
            text = firstDayOfMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + firstDayOfMonth.year,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Days of the week header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
        // Month grid
        Column {
            var day = 1 - firstDayOfWeek
            for (week in 0 until 6) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (dayOfWeek in 0 until 7) {
                        if (day > 0 && day <= daysInMonth) {
                            val currentDate = firstDayOfMonth.plusDays((day - 1).toLong())
                            val isSelected = currentDate == selectedDate
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surface
                                    )
                                    .clickable {
                                        onDateSelected(currentDate)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Box(modifier = Modifier.weight(1f).aspectRatio(1f))
                        }
                        day++
                    }
                }
            }
        }
    }
}