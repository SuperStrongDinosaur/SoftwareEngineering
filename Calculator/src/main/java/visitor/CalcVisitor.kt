package visitor

import token.Bracket
import token.NumberToken
import token.Operation
import token.Token
import java.util.Stack

class CalcVisitor : TokenVisitor {
    private val stack = Stack<Int>()

    override fun visit(token: NumberToken) {
        stack.add(token.value)
    }

    override fun visit(token: Bracket) {}

    override fun visit(token: Operation) {
        val a = stack.pop()
        val b = stack.pop()
        when {
            token.tokenType === Token.TokenType.ADD -> stack.add(a + b)
            token.tokenType === Token.TokenType.SUB -> stack.add(b - a)
            token.tokenType === Token.TokenType.MUL -> stack.add(a * b)
            token.tokenType === Token.TokenType.DIV -> stack.add(b / a)
        }
    }

    fun calculate(tokens: List<Token>): Int {
        for (token in tokens) {
            token.accept(this)
        }
        return stack.pop()
    }
}
