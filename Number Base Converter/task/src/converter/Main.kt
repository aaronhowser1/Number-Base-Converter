package converter

import java.math.BigInteger

// Do not delete this line

fun main() {
    showMenu()
}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    return readln()
}

fun showMenu() {
    val bases = inputFromPrompt("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
    if (bases != "/exit") {
        val sourceBase = bases.split(" ")[0].toInt()
        val targetBase = bases.split(" ")[1].toInt()
        while (true) {
            val sourceNumber = inputFromPrompt("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")
            if (sourceNumber == "/back") break
            println("Conversion result: ${convertArbitrary(sourceBase, sourceNumber, targetBase)}")
        }
        showMenu()
    }
}

fun convertFromDecimal(decimal: BigInteger, radix: Int): String {
    var number = decimal
    val remaindersList = mutableListOf<Int>()

    loop@while (true) {
        when (radix) {
            1 -> {
                var iterator = BigInteger.ONE
                while (iterator <= number) {
                    remaindersList.add(1)
                    iterator++
                }
                break@loop
            }
            0 -> {
                return "Impossible"
            }
            else -> {
                val radixAsBigInt = BigInteger.valueOf(radix.toLong())
                remaindersList.add((number%radixAsBigInt).toInt())
                val newNumber = number/radixAsBigInt
                if (newNumber == BigInteger.ZERO) {
                    break@loop
                } else number = newNumber
            }
        }
    }

    val remaindersListChar = mutableListOf<Char>()

    for (digit in remaindersList) {
        remaindersListChar.add(digitToChar(digit))
    }

    return remaindersListChar.reversed().joinToString("")
}

fun convertToDecimal(sourceChar: String, sourceBase: Int): BigInteger {
    return sourceChar.toBigInteger(sourceBase)
}

fun convertArbitrary(sourceBase: Int, sourceNumber: String, targetBase: Int): String {
    return convertFromDecimal(convertToDecimal(sourceNumber,sourceBase),targetBase)
}

fun digitToChar(digit: Int): Char {
    if (digit in 0..9) {
        return digit.digitToChar()
    } else {
        val amountAbove = digit-10
        return 'A'+amountAbove
    }
}