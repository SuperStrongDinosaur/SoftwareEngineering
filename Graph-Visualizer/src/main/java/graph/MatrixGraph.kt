package graph

import api.DrawingApi
import java.util.*

class MatrixGraph(drawingApi: DrawingApi) : Graph(drawingApi) {
    private var matrix: Array<Array<Int>> = arrayOf()
    private val x: Double = drawingApi.drawingAreaWidth / 2.0
    private val y: Double = drawingApi.drawingAreaHeight / 2.0
    private val r: Long = 5

    override fun readGraph() {
        with(Scanner(System.`in`)) {
            val n = nextInt()

            val temp = mutableListOf<MutableList<Int>>()
            for (i in 0 until n) {
                temp += mutableListOf<Int>()
                for (j in 0 until n) {
                    temp[i].add(nextInt())
                }
            }
            matrix = temp.map { it.toTypedArray() }.toTypedArray()
        }
    }

    override fun drawGraph() {
        val n = matrix.size
        val xs = DoubleArray(n)
        val ys = DoubleArray(n)
        calculateVerticesPositions(n, x, y, xs, ys, 100)
        for (i in 0 until n) {
            drawingApi.drawCircle(xs[i], ys[i], r)
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (matrix[i][j] == 1) {
                    drawingApi.drawLine(xs[i], ys[i], xs[j], ys[j])
                }
            }
        }
        drawingApi.showGraph()
    }
}
