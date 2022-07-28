package br.com.messore.tech.turtleracing.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {

    @TypeConverter
    fun toDate(localTime: String?): LocalTime? {
        return localTime?.let { LocalTime.parse(it) }
    }

    @TypeConverter
    fun toDateString(localTime: LocalTime?): String? {
        return localTime?.toString()
    }
}
