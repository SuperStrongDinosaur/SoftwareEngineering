package api

import graph.getGraphByArg
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.stage.Stage

class JavaFxDrawingApi : Application(), DrawingApi {
    override fun showGraph() {}

    private val root = Group()

    override fun start(primaryStage: Stage) {
        val graph = getGraphByArg(this, parameters.raw[0])
        graph.readGraph()
        graph.drawGraph()

        primaryStage.scene = Scene(root, drawingAreaWidth.toDouble(), drawingAreaHeight.toDouble(), Color.WHITE)
        primaryStage.show()
    }

    override val drawingAreaWidth = 600
    override val drawingAreaHeight = 400

    override fun drawCircle(x: Double, y: Double, r: Long) {
        val circle = Circle(x, y, r.toDouble())
        circle.fill = Color.BLACK
        root.children.add(circle)
    }

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        val line = Line(x1, y1, x2, y2)
        line.stroke = Color.BLACK
        line.strokeWidth = 1.0
        root.children.add(line)
    }
}
