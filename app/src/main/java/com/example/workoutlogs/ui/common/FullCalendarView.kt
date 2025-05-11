// File: app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 23:59:00 CEST
// Scope: Composable for full calendar view in WorkoutLogs app
// Note: Replace the existing FullCalendarView.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/common/FullCalendarView.kt
// with this file. Updated to show single month, ~8 rows (month label, day labels, ~6 date rows),
// positioned at bottom via HomeScreen.kt/WorkoutScreen.kt.
// Retains date selection, integrates with SimpleCalendarView.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, FullCalendarView content
// (single month, month label, day labels, ~8 rows, bottom position).
// If issues:
// 1. Share local FullCalendarView.kt if calendar display or selection fails.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share Logcat for calendar issues.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    val month = selectedDate.month
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0, Monday = 1, etc.
    val daysInMonth = lastDayOfMonth.dayOfMonth
    val monthLabel = month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " $year"
    val dayLabels = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
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
        // Date grid (~6 rows)
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

@Composable
private fun Grid(
    firstDayOfWeek: Int,
    daysInMonth: Int,
    selectedDate: LocalDate,
    year: Int,
    month: java.time.Month,
    onDateSelected: (LocalDate) -> Unit
) {
    val totalCells = 42 // 6 rows * 7 columns
    var day = 1
    repeat(6) { row ->
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