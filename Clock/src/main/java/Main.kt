import clock.SettableClock
import statistic.EventsStatisticImpl

import java.time.Instant

fun main(args: Array<String>) {
    val clock = SettableClock(Instant.now())
    val eventsStatistic = EventsStatisticImpl(clock)

    eventsStatistic.incEvent("a")
    eventsStatistic.incEvent("a")
    eventsStatistic.incEvent("a")
    eventsStatistic.incEvent("a")
    eventsStatistic.incEvent("a")
    eventsStatistic.incEvent("b")
    eventsStatistic.incEvent("b")
    eventsStatistic.incEvent("b")
    //((clock.SettableClock) clock).setNow(Instant.now().plus(Duration.ofHours(1)).plus(Duration.ofMinutes(1)));
    eventsStatistic.printStatistic()
}

