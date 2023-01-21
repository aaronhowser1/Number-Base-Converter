import java.math.BigDecimal
import java.math.RoundingMode     

fun main() {
    // write your code here
    val power = readln().toInt()
    val mode = readln().toInt()
    val number = readln().toBigDecimal()

    var output = number.setScale(mode, BigDecimal.ROUND_FLOOR)
    output = output.pow(power)

    println(output)
}