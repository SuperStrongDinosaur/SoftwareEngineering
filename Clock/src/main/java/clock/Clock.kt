package clock

import java.time.Instant

interface Clock {
    val nowInMillis: Long
    fun now(): Instant
}
