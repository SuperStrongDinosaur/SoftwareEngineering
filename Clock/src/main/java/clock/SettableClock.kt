package clock

import java.time.Instant

class SettableClock(private var now: Instant) : Clock {
    override val nowInMillis: Long
        get() =  now.toEpochMilli()

    fun setNow(now: Instant) {
        this.now = now
    }

    override fun now(): Instant {
        return now
    }

}
