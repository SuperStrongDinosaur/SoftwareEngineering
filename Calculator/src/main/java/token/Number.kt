package token

import visitor.TokenVisitor

class NumberToken(s: String) : Token {
    val value: Int = Integer.parseInt(s)

    override val tokenType: Token.TokenType
        get() = Token.TokenType.NUM

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "NUMBER($value)"
    }
}
