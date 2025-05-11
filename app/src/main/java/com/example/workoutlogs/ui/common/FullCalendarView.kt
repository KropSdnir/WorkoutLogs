// File: app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-12 01:30:00 CEST
// Scope: Composable for full calendar view in WorkoutLogs app
// Note: Replace the existing FullCalendarView.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// with this file. Updated to show all months in a year, scrollable in cards,
// each with 7 rows (month label, day labels, 5 date rows), as ModalBottomSheet overlay.
// Fixed R81 bug where all months showed as "June 2026".
// Retains date selection, integrates with SimpleCalendarView.
// Sourced from R82 provided code.
// Verify this file is applied correctly by checking the Timestamp, FullCalendarView content
// (scrollable months, correct month labels, 7 rows, overlay behavior).
// If issues:
// 1. Share local FullCalendarView.kt if month display, scrolling, or selection fails.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share Logcat for calendar issues.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts.

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val year = selectedDate.year
    val startDate = LocalDate.of(year, 1, 1) // Start at January
    val endDate = LocalDate.of(year, 12, 31) // End at December
    val dayLabels = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(12) { monthIndex ->
            val currentDate = startDate.plusMonths(monthIndex.toLong())
            MonthCard(
                date = currentDate,
                selectedDate = selectedDate,
                dayLabels = dayLabels,
                onDateSelected = onDateSelected
            )
        }
    }
}

@Composable
private fun MonthCard(
    date: LocalDate,
    selectedDate: LocalDate,
    dayLabels: List<String>,
    onDateSelected: (LocalDate) -> Unit
) {
    val year = date.year
    val month = date.month
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0, Monday = 1, etc.
    val daysInMonth = lastDayOfMonth.dayOfMonth
    val monthLabel = month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " $year"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Month label
            Text(
                text = monthLabel,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
            // Day labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                dayLabels.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            // Date grid (5 rows)
            Grid(
                firstDayOfWeek = firstDayOfWeek,
                daysInMonth = daysInMonth,
                selectedDate = selectedDate,
                year = year,
                month = month,
                onDateSelected = onDateSelected
            )
        }
    }
}

@Composable
private fun Grid(
    firstDayOfWeek: Int,
    daysInMonth: Int,
    selectedDate: LocalDate,
    year: Int,
    month: java.time.Month,
    onDateSelected: (LocalDate) -> Unit
) {
    var day = 1
    repeat(5) { row -> // 5 rows for dates
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(7) { column ->
                val index = row * 7 + column
                val isValidDay = index >= firstDayOfWeek && day <= daysInMonth
                val date = if (isValidDay) {
                    LocalDate.of(year, month, day)
                } else {
                    null
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(2.dp)
                        .background(
                            if (date == selectedDate) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface
                        )
                        .clickable(enabled = isValidDay) {
                            if (isValidDay && date != null) {
                                onDateSelected(date)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (isValidDay) {
                        Text(
                            text = day.toString(),
                            color = if (date == selectedDate) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                        if (isValidDay) day++
                    }
                }
            }
        }
    }
}