package statistic

interface EventsStatistic {
    val allEventStatistic: Map<String, Double>
    fun incEvent(name: String)
    fun getEventStatisticByName(name: String): Double
    fun printStatistic()
}
