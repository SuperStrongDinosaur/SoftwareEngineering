package visitor

import token.Bracket
import token.NumberToken
import token.Operation
import token.Token

import java.util.ArrayList
import java.util.Stack

class ParserVisitor : TokenVisitor {
    private val result = ArrayList<Token>()
    private val stack = Stack<Token>()

    override fun visit(token: NumberToken) {
        result.add(token)
    }

    override fun visit(token: Bracket) {
        if (token.tokenType === Token.TokenType.LEFT) {
            stack.add(token)
        } else {
            while (!stack.isEmpty() && stack.peek().tokenType !== Token.TokenType.LEFT) {
                result.add(stack.pop())
            }
            if (stack.isEmpty()) {
                throw IllegalStateException("Unexpected end of stack")
            }
            stack.pop()
        }
    }

    override fun visit(token: Operation) {
        while (!stack.isEmpty()) {
            val operation = stack.peek() as? Operation ?: break
            if (getOperationPriority(operation) >= getOperationPriority(token)) {
                result.add(operation)
                stack.pop()
            } else {
                break
            }
        }
        stack.push(token)
    }

    fun transformToReversePolishNotation(tokens: List<Token>): List<Token> {
        for (token in tokens) {
            token.accept(this)
        }
        while (!stack.isEmpty()) {
            val element = stack.peek()
            stack.pop()
            if (element is Bracket) {
                throw IllegalStateException("Incorrect input expression: mismatched brackets")
            }
            result.add(element)
        }
        return result
    }

    private fun getOperationPriority(token: Operation): Int {
        if (token.tokenType === Token.TokenType.ADD || token.tokenType === Token.TokenType.SUB) {
            return 0
        }
        if (token.tokenType === Token.TokenType.MUL || token.tokenType === Token.TokenType.DIV) {
            return 1
        }
        throw IllegalArgumentException("Unknown operation type")
    }
}
