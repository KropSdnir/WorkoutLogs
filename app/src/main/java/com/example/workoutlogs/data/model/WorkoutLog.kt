// app/src/main/java/com/example/workoutlogs/data/model/WorkoutLog.kt
// Timestamp: 2025-05-14 06:22:00
// Scope: Room entity for storing workout logs in WorkoutLogs app

package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "workout_logs")
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseId: Int,
    val date: LocalDate,
    val sets: Int? = null,
    val reps: Int? = null,
    val weight: Double? = null
)