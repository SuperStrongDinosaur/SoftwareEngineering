import main.Counter

fun main(args: Array<String>) {
    val producer = Counter()
    System.out.println(producer.getDiagram("кек", 24))
}
