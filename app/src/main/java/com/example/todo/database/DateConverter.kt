package com.example.todo.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromeDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }
}