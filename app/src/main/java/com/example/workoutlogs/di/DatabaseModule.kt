// app/src/main/java/com/example/workoutlogs/di/DatabaseModule.kt
// Timestamp: 2025-05-14 18:43:00
// Scope: Hilt module for providing Room database dependencies in WorkoutLogs app

package com.example.workoutlogs.di

import android.content.Context
import androidx.room.Room
import com.example.workoutlogs.data.db.AppDatabase
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.db.dao.WorkoutLogDao
import com.example.workoutlogs.data.model.Exercise
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "workout_logs_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideExerciseDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    fun provideWorkoutLogDao(database: AppDatabase): WorkoutLogDao {
        return database.workoutLogDao()
    }

    @Provides
    fun provideDefaultExercises(): List<Exercise> {
        return listOf(
            Exercise(
                id = 1,
                name = "Squat",
                category = "Legs",
                notes = "Barbell back squat",
                isSelected = false
            ),
            Exercise(
                id = 2,
                name = "Bench Press",
                category = "Chest",
                notes = "Barbell bench press",
                isSelected = false
            ),
            Exercise(
                id = 3,
                name = "Deadlift",
                category = "Back",
                notes = "Conventional deadlift",
                isSelected = false
            )
        )
    }
}