// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/model/Exercise.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Room entity representing an exercise within a workout
package com.workoutlogs.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(
        entity = Workout::class,
        parentColumns = ["id"],
        childColumns = ["workoutId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutId: Int,
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Float
)