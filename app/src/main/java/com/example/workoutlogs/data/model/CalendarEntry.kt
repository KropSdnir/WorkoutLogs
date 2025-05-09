// File: app/src/main/java/com/example/workoutlogs/data/model/CalendarEntry.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:15:00
// Scope: Room entity for calendar entries in WorkoutLogs app

package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "calendar_entries")
data class CalendarEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val workoutName: String,
    val sets: Int
)