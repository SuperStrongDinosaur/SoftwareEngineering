package db

import com.mongodb.rx.client.MongoClient
import com.mongodb.rx.client.MongoClients
import com.mongodb.rx.client.Success
import db.entities.Good
import db.entities.User
import rx.Observable

import java.util.concurrent.TimeUnit

import com.mongodb.client.model.Filters.eq


class ReactiveMongoDriver {
    private val client = createMongoClient()

    private fun createMongoClient(): MongoClient {
        return MongoClients.create("mongodb://localhost:27017")
    }

    fun createUser(user: User): Success {
        if(client != null)
            return client.getDatabase("rx").getCollection("user")
                    .insertOne(user.toDocument()).timeout(10, TimeUnit.SECONDS).toBlocking().single()
        return Success.SUCCESS
    }

    fun addGoods(good: Good): Success {
        if(client != null)
            return client.getDatabase("rx").getCollection("goods")
                    .insertOne(good.toDocument()).timeout(10, TimeUnit.SECONDS).toBlocking().single()
        return Success.SUCCESS
    }

    fun getAllGoods(id: Int): Observable<String> {
        return findCurrencyByUserId(id).flatMap { currency ->
            client.getDatabase("rx").getCollection("goods").find()
                    .toObservable().map { d -> Good(d).toString(currency) }.reduce { str1, str2 -> str1 + "\n" + str2 }
        }
    }

    private fun findCurrencyByUserId(id: Int): Observable<String> {
        return client.getDatabase("rx").getCollection("user").find(eq<Int>("id", id)).first().map { d -> User(d).currency }
    }
}
