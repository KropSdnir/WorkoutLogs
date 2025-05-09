// File: app/src/main/java/com/example/workoutlogs/data/model/CalendarEntry.kt
// Timestamp: Updated on 2025-05-09 20:00:00
// Scope: Data model for calendar entries stored in Room database

package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "calendar_entries")
data class CalendarEntry(
    @PrimaryKey val date: LocalDate,
    val note: String? = null
)