package server

import com.mongodb.rx.client.Success
import db.ReactiveMongoDriver
import db.entities.Good
import db.entities.User
import rx.Observable

import java.util.*

class Methods(val db: ReactiveMongoDriver) {
    private val USER_PARAMS = Arrays.asList("id", "name", "currency")
    private val GOOD_PARAMS = Arrays.asList("id", "name", "eur", "usd", "rub")
    private val GET_GOODS_PARAMS = listOf("user_id")

    private val db = ReactiveMongoDriver()

    fun addUser(params: Map<String, List<String>>): Observable<String> {
        val error = check(params, USER_PARAMS)
        if (!error.endsWith("field(s) ")) {
            return Observable.just(error)
        }
        val id = Integer.parseInt(params["id"]!![0])
        val name = params["name"]!![0]
        val currency = params["currency"]!![0]
        val newUser = User(id, name, currency)
        return if (db.createUser(newUser) == Success.SUCCESS) {
            Observable.just("New user added\n$newUser")
        } else {
            Observable.just("Failed")
        }
    }

    fun addGood(params: Map<String, List<String>>): Observable<String> {
        val error = check(params, GOOD_PARAMS)
        if (!error.endsWith("field(s) ")) {
            return Observable.just(error)
        }
        val id = Integer.parseInt(params["id"]!![0])
        val name = params["name"]!![0]
        val eur = params["eur"]!![0]
        val usd = params["usd"]!![0]
        val rub = params["rub"]!![0]
        val newGood = Good(id, name, eur, usd, rub)
        return if (db.addGoods(newGood) == Success.SUCCESS) {
            Observable.just("New good added\n$newGood")
        } else {
            Observable.just("Failed")
        }
    }

    fun getGoods(params: Map<String, List<String>>): Observable<String> {
        val error = check(params, GET_GOODS_PARAMS)
        if (!error.endsWith("field(s) ")) {
            return Observable.just(error)
        }
        val id = Integer.parseInt(params["user_id"]!![0])
        return db.getAllGoods(id)
    }

    private fun check(params: Map<String, List<String>>, values: List<String>): String {
        val error = StringJoiner(", ", "Please fill in field(s) ", "")
        values.stream().filter { value -> !params.containsKey(value) }.forEach{ error.add(it) }
        return error.toString()
    }
}
