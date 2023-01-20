package converter // Do not delete this line

fun main() {
    showMenu()
}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    return readln()
}

fun showMenu() {

    loop@ while (true) {
        val choice = inputFromPrompt("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        when (choice) {
            "/from" -> {
                val decimalNumber = inputFromPrompt("Enter number in decimal system:").toInt()
                val targetBase = inputFromPrompt("Enter target base:").toInt()

                println("Conversion result: ${convertFromDecimal(decimalNumber,targetBase)}")
            }
            "/to" -> {
                val sourceNumber = inputFromPrompt("Enter source number:")
                val sourceBase = inputFromPrompt("Enter source base:").toInt()
                when (sourceBase) {
                    2,8,16-> println("Conversion to decimal result: ${convertToDecimal(sourceNumber,sourceBase)}")
                }
            }
            "/exit" -> break@loop
        }
    }
}

fun convertFromDecimal(decimal: Int, radix: Int): String {

    var number = decimal
    val remaindersList = mutableListOf<Int>()

    while (true) {
        if (radix == 1) {
            for (i in 1..number) remaindersList.add(1)
            break
        } else if (radix == 0) {
            return "Impossible"
        } else {
            remaindersList.add(number%radix)
            val newNumber = number/radix
            if (newNumber == 0) break else number = newNumber
        }
    }

    val remaindersListChar = mutableListOf<Char>()

    for (digit in remaindersList) {
        remaindersListChar.add(digitToChar(digit))
    }

    return remaindersListChar.reversed().joinToString("")
}

fun convertToDecimal(sourceChar: String, sourceBase: Int): Int {
    return Integer.parseInt(sourceChar,sourceBase)
}

fun digitToChar(digit: Int): Char {
    if (digit in 0..9) {
        return digit.digitToChar()
    } else {
        val amountAbove = digit-10
        return 'A'+amountAbove
    }

}