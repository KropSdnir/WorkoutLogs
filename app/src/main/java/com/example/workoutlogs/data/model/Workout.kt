// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/model/Workout.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Room entity representing a workout (weight or cardio)
package com.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // "weight" or "cardio"
    val date: Long, // Timestamp
    val details: String // JSON or string representation of exercise details
)