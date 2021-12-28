package statistic

import clock.SettableClock
import org.junit.Assert
import org.junit.Test

import java.time.Duration
import java.time.Instant

class EventsStatisticTest {

    @Test
    fun statisticTest() {
        val clock = SettableClock(Instant.now())
        val eventsStatistic = EventsStatisticImpl(clock)

        for (i in 0..99) {
            eventsStatistic.incEvent("a")
        }
        for (i in 0..9) {
            eventsStatistic.incEvent("b")
        }
        for (i in 0..4) {
            eventsStatistic.incEvent("c")
        }

        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("a") - 100 / 60.0) <= EPS)
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("b") - 10 / 60.0) <= EPS)
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("c") - 5 / 60.0) <= EPS)

        clock.setNow(Instant.now().plus(Duration.ofHours(1)).plus(Duration.ofMinutes(1)))

        for (i in 0..7) {
            eventsStatistic.incEvent("c")
        }

        Assert.assertEquals(0.0, eventsStatistic.getEventStatisticByName("a"), EPS)
        Assert.assertEquals(0.0, eventsStatistic.getEventStatisticByName("b"), EPS)
        Assert.assertTrue(Math.abs(eventsStatistic.getEventStatisticByName("c") - 8 / 60.0) <= EPS)
    }

    private val EPS = 1e-8
}
