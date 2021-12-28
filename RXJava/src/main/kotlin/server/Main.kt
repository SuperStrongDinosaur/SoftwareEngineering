package server

import db.ReactiveMongoDriver
import io.reactivex.netty.protocol.http.server.HttpServer
import rx.Observable

fun main(args: Array<String>) {
    val russian = RussianAnalyzer()

    val d = ReactiveMongoDriver()
    val m = Methods(d)
    HttpServer.newServer(8081).start { request, response ->
        val method = request.decodedPath.substring(1)
        val params = request.queryParameters
        when (method) {
            "addUser" -> response.writeString(m.addUser(params))
            "addGood" -> response.writeString(m.addGood(params))
            "getGoods" -> response.writeString(m.getGoods(params))
            else -> response.writeString(Observable.just("Unknown command"))
        }
    }.awaitShutdown()
}

