// app/src/main/java/com/example/workoutlogs/di/DatabaseModule.kt
// Timestamp: 2025-05-14 21:55:00 CEST
// Scope: Hilt module for providing database and repository dependencies in WorkoutLogs app

package com.example.workoutlogs.di

import android.content.Context
import androidx.room.Room
import com.example.workoutlogs.data.db.AppDatabase
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.repository.ExerciseRepository
import com.example.workoutlogs.data.repository.ExerciseRepositoryImpl
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "workout_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository {
        return ExerciseRepositoryImpl(exerciseDao)
    }
}