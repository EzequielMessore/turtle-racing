package br.com.messore.tech.turtleracing.domain.extensions

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val defaultPattern = "dd/MM/yyyy HH:mm:ss"

fun LocalTime.toDuration(): Duration {
    return Duration
        .ofHours(hour.toLong())
        .plusMinutes(minute.toLong())
        .plusSeconds(second.toLong())
}

fun LocalDateTime.plusDuration(duration: Duration): LocalDateTime {
    return plusSeconds(duration.seconds)
}

fun LocalDateTime.format(pattern: String = defaultPattern): String {
    return DateTimeFormatter.ofPattern(pattern).format(this)
}
