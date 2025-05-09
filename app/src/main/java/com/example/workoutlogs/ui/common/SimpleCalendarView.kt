// File: app/src/main/java/com/example/workoutlogs/ui/common/SimpleCalendarView.kt
// Timestamp: Updated on 2025-05-09 14:00:00
// Scope: Displays a single-row date ("Today" or formatted full date like "Friday, May 4, 2025")

package com.example.workoutlogs.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SimpleCalendarView() {
    val today = LocalDate.now()
    val selectedDate = remember { mutableStateOf(today) }
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (selectedDate.value == today) "Today" else selectedDate.value.format(formatter),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}