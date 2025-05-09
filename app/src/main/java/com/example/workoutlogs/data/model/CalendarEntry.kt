package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.workoutlogs.data.db.converters.LocalDateConverter
import java.time.LocalDate

// File: app/src/main/java/com/example/workoutlogs/data/db/CalendarEntry.kt
// Timestamp: Updated on 2025-05-09 05:50:00
// Scope: Room entity for storing workout log entries

@Entity(tableName = "calendar_entries")
@TypeConverters(LocalDateConverter::class)
data class CalendarEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val workoutName: String,
    val sets: Int
)