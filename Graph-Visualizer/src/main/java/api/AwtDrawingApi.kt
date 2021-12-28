package api

import graph.Circle
import graph.Line

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D

class AwtDrawingApi : Frame(), DrawingApi {
    private val circles = mutableListOf<Circle>()
    private val lines = mutableListOf<Line>()

    override val drawingAreaWidth = 600
    override val drawingAreaHeight = 400

    override fun drawCircle(x: Double, y: Double, r: Long) {
        circles += Circle(x, y, r)
        repaint()
    }

    override fun drawLine(x1: Double, y1: Double, x2: Double, y2: Double) {
        lines += Line(x1, y1, x2, y2)
        repaint()
    }

    override fun paint(gInit: Graphics) {
        val g = gInit as Graphics2D
        g.paint = Color.BLACK
        g.stroke = BasicStroke(1.0f)

        for (line in lines) {
            g.draw(Line2D.Double(line.x1, line.y1, line.x2, line.y2))
        }
        for (circle in circles) {
            g.fill(Ellipse2D.Double(circle.x - circle.r, circle.y - circle.r, 2 * circle.r.toDouble(), 2 * circle.r.toDouble()))
        }
    }

    override fun showGraph() {
        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(we: WindowEvent) {
                System.exit(0)
            }
        })
        isVisible = true
        setSize(drawingAreaWidth, drawingAreaHeight)
    }
}
