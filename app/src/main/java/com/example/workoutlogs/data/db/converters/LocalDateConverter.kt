// File: app/src/main/java/com/example/workoutlogs/data/db/converters/LocalDateConverter.kt
// Timestamp: Updated on 2025-05-09 20:00:00
// Scope: Type converter for LocalDate in Room database

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