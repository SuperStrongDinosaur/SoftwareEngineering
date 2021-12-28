package token

import visitor.TokenVisitor

interface Token {
    val tokenType: TokenType
    fun accept(visitor: TokenVisitor)

    enum class TokenType(private val str: String) {
        NUM("NUMBER"),
        ADD("PLUS"),
        SUB("MINUS"),
        MUL("MUL"),
        DIV("DIV"),
        LEFT("LEFT"),
        RIGHT("RIGHT");

        override fun toString(): String {
            return str
        }

        companion object {
            internal fun getTokenTypeByChar(c: Char): TokenType {
                return when (c) {
                    '+' -> ADD
                    '-' -> SUB
                    '*' -> MUL
                    '/' -> DIV
                    '(' -> LEFT
                    ')' -> RIGHT
                    else -> throw IllegalArgumentException("Unexpected token")
                }
            }
        }
    }
}
