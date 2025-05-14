// app/src/main/java/com/example/workoutlogs/data/db/converters/LocalDateConverter.kt
// Timestamp: 2025-05-14 06:22:00
// Scope: Room type converter for LocalDate in WorkoutLogs app

package com.example.workoutlogs.data.db.converters

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }
}