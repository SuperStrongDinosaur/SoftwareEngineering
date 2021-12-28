package visitor

import token.Bracket
import token.NumberToken
import token.Operation
import token.Token

import java.util.ArrayList

class PrintVisitor : TokenVisitor {
    private val result = ArrayList<String>()

    override fun visit(token: NumberToken) {
        result.add(token.toString())
    }

    override fun visit(token: Bracket) {
        result.add(token.toString())
    }

    override fun visit(token: Operation) {
        result.add(token.toString())
    }

    fun transformToString(tokens: List<Token>): String {
        for (token in tokens) {
            token.accept(this)
        }
        return result.joinToString(" ")
    }
}
