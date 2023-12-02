package year2023.day1

import loadResource
import java.util.*

fun main(args: Array<String>) {
    val measurements = loadResource("year2023/day1/CalibrationDocument").readLines()
    println(getCalibrationSum(measurements))
    println(getCalibrationSumWithNonDigits(measurements))
}

fun getCalibrationSum(measurements: List<String>): Int {
    return measurements.sumOf {
        val firstDigit = it.first { symbol -> symbol.isDigit() }
        val lastDigit = it.last { symbol -> symbol.isDigit() }
        "$firstDigit$lastDigit".toInt()
    }
}

fun getCalibrationSumWithNonDigits(measurements: List<String>): Int {
    return measurements.sumOf {
        val measurementLine = prepare(it);
        val firstDigit = measurementLine.first { symbol -> symbol.isDigit() }
        val lastDigit = measurementLine.last { symbol -> symbol.isDigit() }
        "$firstDigit$lastDigit".toInt()
    }
}

fun prepare(line: String) =
    line
        .replace("one", "o1e")
        .replace("two", "t2o")
        .replace("three", "t3e")
        .replace("four", "f4r")
        .replace("five", "f5e")
        .replace("six", "s6x")
        .replace("seven", "s7n")
        .replace("eight", "e8t")
        .replace("nine", "n9e")
