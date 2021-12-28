package graph

import api.DrawingApi
import java.util.*

class EdgeListGraph(drawingApi: DrawingApi) : Graph(drawingApi) {
    private var graph: Array<MutableList<Int>> = arrayOf()
    private val x: Double = drawingApi.drawingAreaWidth / 2.0
    private val y: Double = drawingApi.drawingAreaHeight / 2.0
    private val r: Long = 5

    override fun readGraph() {
        with(Scanner(System.`in`)) {
            val n = nextInt()
            val m = nextInt()
            graph = arrayOfNulls<MutableList<*>>(n) as Array<MutableList<Int>>
            for (i in 0 until n) {
                graph[i] = ArrayList()
            }
            for (i in 1..m) {
                val u = nextInt()
                val v = nextInt()
                graph[u].add(v)
                graph[v].add(u)
            }
        }
    }

    override fun drawGraph() {
        val n = graph.size
        val xs = DoubleArray(n)
        val ys = DoubleArray(n)
        calculateVerticesPositions(n, x, y, xs, ys, 100)
        for (i in 0 until n) {
            drawingApi.drawCircle(xs[i], ys[i], r)
        }
        for (u in 0 until n) {
            for (v in graph[u]) {
                drawingApi.drawLine(xs[u], ys[u], xs[v], ys[v])
            }
        }
        drawingApi.showGraph()
    }
}
