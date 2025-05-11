// File: app/src/main/java/com/example/workoutlogs/ui/common/SimpleCalendarView.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 20:30:00 CEST
// Scope: Single-row date display composable for WorkoutLogs app
// Note: Replace this file at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/common/SimpleCalendarView.kt
// with this file. Updated to center-align date text.
// Displays selected date (e.g., "Today" or "Thursday, April 15, 2026").
// Used in HomeScreen and WorkoutScreen, clickable to show FullCalendarView.
// Sourced from https://github.com/KropSdnir/WorkoutLogs (ui/common/).
// Verify this file is applied correctly by checking the Timestamp and centered date display.
// If incorrect:
// 1. Share local SimpleCalendarView.kt from ui/common/.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@Composable
fun SimpleCalendarView(
    selectedDate: LocalDate,
    onClick: () -> Unit
) {
    val isToday = selectedDate == LocalDate.now()
    val displayText = if (isToday) {
        "Today"
    } else {
        selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " +
                selectedDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))
    }

    Text(
        text = displayText,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    )
}