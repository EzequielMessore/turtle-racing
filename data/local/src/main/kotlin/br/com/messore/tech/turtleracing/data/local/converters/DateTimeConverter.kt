package br.com.messore.tech.turtleracing.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DateTimeConverter {
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return dateString?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}
