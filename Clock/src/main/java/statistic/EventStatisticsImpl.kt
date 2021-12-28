package statistic

import clock.Clock

import java.util.HashMap
import java.util.LinkedList
import java.util.Queue

class EventsStatisticImpl(private val clock: Clock) : EventsStatistic {
    override val allEventStatistic: Map<String, Double>
        get()  {
            val result = HashMap<String, Double>()
            for (name in events.keys) {
                result[name] = getEventStatisticByName(name)
            }
            return result
        }

    private val events = HashMap<String, Queue<Long>>()

    override fun incEvent(name: String) {
        if (!events.containsKey(name)) {
            events[name] = LinkedList<Long>()
        }
        events[name]!!.add(clock.nowInMillis)
    }

    override fun getEventStatisticByName(name: String): Double {
        if (!events.containsKey(name)) {
            return 0.0
        }
        val queue = events[name]
        val currentTime = clock.nowInMillis
        if (queue != null) {
            while (!queue.isEmpty() && currentTime - queue.peek() > MILLIS_IN_HOUR) {
                queue.poll()
            }
        }
        return queue!!.size / 60.0
    }

    override fun printStatistic() {
        println("Statistic:")
        for ((key, value) in allEventStatistic) {
            println(String.format("%.5f rpm for event %s", value, key))
        }
    }

    private val MILLIS_IN_HOUR = 60 * 60 * 1000
}
