package db.entities

import org.bson.Document

import java.util.HashMap

class Good(private val id: Int, private val name: String, eur: String, usd: String, rub: String) {
    private val currencyMap: MutableMap<String, String>

    fun toDocument(): Document {
        return Document("id", id).append("name", name).append(EUR, currencyMap[EUR]).append(USD, currencyMap[USD]).append(RUB, currencyMap[RUB])
    }

    constructor(doc: Document) : this(doc.getInteger("id")!!, doc.getString("name"), doc.getString(EUR), doc.getString(USD), doc.getString(RUB))

    init {
        this.currencyMap = HashMap()
        currencyMap[EUR] = eur
        currencyMap[USD] = usd
        currencyMap[RUB] = rub
    }

    fun toString(currency: String): String {
        val price = "cost " + currency + "=" + currencyMap[currency]
        return "Good #$id, name is $name, $price"
    }

    override fun toString(): String {
        val pricesBuilder = StringBuilder()
        for ((key, value) in currencyMap) {
            pricesBuilder.append(key).append("=").append(value).append(", ")
        }
        val prices = pricesBuilder.toString()
        return "Good #" + id + ", name is " + name + ", " + prices.substring(0, prices.length - 2)
    }

    companion object {
        private const val EUR = "eur"
        private const val USD = "usd"
        private const val RUB = "rub"
    }
}
