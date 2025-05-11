// File: app/src/main/java/com/example/workoutlogs/data/model/Exercise.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-10 00:23:00
// Scope: Room entity for exercises in WorkoutLogs app

package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val notes: String?,
    val isSelected: Boolean = false
)