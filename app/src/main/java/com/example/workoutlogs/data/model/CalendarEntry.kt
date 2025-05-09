// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/model/CalendarEntry.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Room entity representing a calendar entry for workout tracking
package com.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar_entries")
data class CalendarEntry(
    @PrimaryKey val date: Long, // Timestamp
    val workoutId: Int
)