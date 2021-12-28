package graph

import api.DrawingApi

abstract class Graph (var drawingApi: DrawingApi) {
    abstract fun readGraph()
    abstract fun drawGraph()

    protected fun calculateVerticesPositions(n: Int, x: Double, y: Double, xs: DoubleArray, ys: DoubleArray, r: Int) {
        for (i in 0 until n) {
            val c = 2 * Math.PI / n * i
            xs[i] = x + Math.cos(c) * r
            ys[i] = y + Math.sin(c) * r
        }
    }
}

fun getGraphByArg(api: DrawingApi, arg: String) : Graph {
    return when (arg) {
        "e" -> EdgeListGraph(api)
        "m" -> MatrixGraph(api)
        else -> throw IllegalArgumentException("Graph must be either e or m")
    }
}
