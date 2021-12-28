package token

import visitor.TokenVisitor

class Bracket internal constructor(c: Char) : Token {
    override val tokenType: Token.TokenType = Token.TokenType.getTokenTypeByChar(c)

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return tokenType.toString()
    }
}
