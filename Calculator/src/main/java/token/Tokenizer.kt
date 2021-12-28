package token

import java.util.ArrayList

class Tokenizer {

    fun tokenize(expression: String): List<Token> {
        val tokens = ArrayList<Token>()
        var i = 0
        while (i < expression.length) {
            var c = expression[i]
            while (isWhitespaceCharacter(c)) {
                i++
                c = expression[i]
            }
            when {
                isBracket(c) -> tokens.add(Bracket(c))
                isOperation(c) -> tokens.add(Operation(c))
                Character.isDigit(c) -> {
                    var num = c.toString()
                    var pos = i + 1
                    while (pos < expression.length && Character.isDigit(expression[pos])) {
                        num += expression[pos].toString()
                        pos++
                    }
                    i = pos - 1
                    tokens.add(NumberToken(num))
                } else -> throw IllegalStateException("Couldn't parse expression at position $i")
            }
            i++
        }
        return tokens
    }

    private fun isWhitespaceCharacter(c: Char): Boolean {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t'
    }

    private fun isBracket(c: Char): Boolean {
        return c == '(' || c == ')'
    }

    private fun isOperation(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/'
    }
}
