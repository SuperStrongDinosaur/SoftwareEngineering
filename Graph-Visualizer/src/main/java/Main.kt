import api.AwtDrawingApi
import api.JavaFxDrawingApi
import graph.getGraphByArg
import javafx.application.Application

fun main(args: Array<String>) {
    if (args.size != 2) {
        System.err.println("Wrong input! 1)Available drawing apis: awt - java.awt, fx - javafx. 2)Available formats: e - edge list, m - matrix")
    }
    when (args[0]) {
        "awt" -> {
            val drawer = AwtDrawingApi()
            val graph = getGraphByArg(drawer, args[1])
            graph.readGraph()
            graph.drawGraph()
        }
        "fx" -> {
            Application.launch(JavaFxDrawingApi::class.java, args[1])
        }
        else -> throw IllegalArgumentException("Drawer must be either awt or fx")
    }
}

