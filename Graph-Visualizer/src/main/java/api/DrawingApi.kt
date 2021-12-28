package api

interface DrawingApi {
    val drawingAreaWidth: Int
    val drawingAreaHeight: Int
    fun drawCircle(x: Double, y: Double, r: Long)
    fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double)
    fun showGraph()
}
