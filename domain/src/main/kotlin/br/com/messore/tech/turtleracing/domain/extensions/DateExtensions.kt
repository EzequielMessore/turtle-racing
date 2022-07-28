package br.com.messore.tech.turtleracing.domain.extensions

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

private const val timePattern = "HH:mm:ss"
private const val defaultPattern = "dd/MM/yyyy $timePattern"

fun LocalTime.toDuration(): Duration {
    return Duration
        .ofHours(hour.toLong())
        .plusMinutes(minute.toLong())
        .plusSeconds(second.toLong())
}

fun LocalDateTime.plusDuration(duration: Duration): LocalDateTime {
    return plusSeconds(duration.seconds)
}

fun String.toLocalTime(): LocalTime {
    return runCatching {
        LocalTime.parse(this)
    }.getOrDefault(LocalTime.of(0, 0, 30))
}

fun String.toLocalDateTime(pattern: String = defaultPattern): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern))
}

fun LocalDateTime.format(pattern: String = defaultPattern): String {
    return DateTimeFormatter.ofPattern(pattern).format(this)
}

fun Long.formatTime(pattern: String = timePattern): String {
    val seconds = LocalTime.ofSecondOfDay(this / 1000)
    return DateTimeFormatter.ofPattern(pattern).format(seconds)
}

fun LocalDateTime.toMillis(): Long {
    val zonedDateTime = ZonedDateTime.of(this, ZoneId.systemDefault())
    return zonedDateTime.toInstant().toEpochMilli()
}

fun LocalDateTime.getDiffInSeconds(nextDate: LocalDateTime = nowToNextDay()): Long {
    val millis = Duration.between(this, nextDate).toMillis()
    return TimeUnit.MILLISECONDS.toSeconds(millis)
}

private fun nowToNextDay(): LocalDateTime {
    return LocalDateTime.now() toNextDayAt 6
}

private infix fun LocalDateTime.toNextDayAt(hour: Int): LocalDateTime {
    return plusDays(1).with(LocalTime.MIDNIGHT).withHour(hour)
}
