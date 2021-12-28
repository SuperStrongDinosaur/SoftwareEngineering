import token.Tokenizer
import visitor.CalcVisitor
import visitor.ParserVisitor
import visitor.PrintVisitor
import java.util.*

fun main(args: Array<String>) {
    with(Scanner(System.`in`)) {
        val expression = nextLine()
        val tokens = Tokenizer().tokenize(expression)
        //println(PrintVisitor().transformToString(tokens))
        val reversedTokens = ParserVisitor().transformToReversePolishNotation(tokens)
        println(PrintVisitor().transformToString(reversedTokens))
        println(CalcVisitor().calculate(reversedTokens))
    }
}

