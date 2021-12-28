package db.entities

import org.bson.Document
import io.netty.channel.unix.Errors



class User(val id: Int, val name: String, val currency: String) {

    fun toDocument(): Document {
        return Document("id", id).append("name", name).append("currency", currency)
    }

    constructor(doc: Document) : this(doc.getInteger("id")!!, doc.getString("name"), doc.getString("currency"))

    override fun toString(): String {
        return "User #$id, name is $name, currency=$currency"
    }
}

import io.netty.channel.unix.Errors

