// app/src/main/java/com/example/workoutlogs/data/model/WorkoutLog.kt
// Timestamp: 2025-05-14 21:32:00 CEST
// Scope: Room entity for workout logs in WorkoutLogs app

package com.example.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_log")
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String
)