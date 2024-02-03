import java.util.*

fun isHtmlMatched(html: String): Boolean {
    val stack: Stack<String> = Stack()

    var idx = html.indexOf('<')
    while (idx != -1) {

        val end = html.indexOf('>', idx + 1)

        if (end == -1) return false

        val tag = html.substring(idx, end + 1)

        if (tag.contains('/')) {
            if (stack.isEmpty()) return false

            val pair = tag.filterNot { it == '/' }
            if (stack.peek() == pair) {
                stack.pop()
            } else {
                return false
            }
        } else {
            stack.push(tag)
        }

        idx = html.indexOf('<', end + 1)
    }

    return stack.isEmpty()
}


fun main() {
    println(
        isHtmlMatched(
            "<body>\n" +
                    "<center>\n" +
                    "<h1> The Little Boat </h1> </center>\n" +
                    "<p> The storm tossed the little boat like a cheap sneaker in an old washing machine. The three drunken fishermen were used to such treatment, of course, but not the tree salesman, who even as a stowaway now felt that he\n" +
                    "had overpaid for the voyage. </p> <ol>\n" +
                    "<li> Will the salesman die? </li> <li> What color is the boat? </li> <li> And what about Naomi? </li> </ol>\n" +
                    "</body>"
        )
    )
}