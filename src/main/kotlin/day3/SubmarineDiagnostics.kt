package day3

import loadResource
import java.io.File


fun main(args: Array<String>) {

    val diagnostics = loadResource("day3/Diagnostics")
    println(calculatePowerConsumption(diagnostics))
    println(calculateLifeSupport(diagnostics))
}

fun calculatePowerConsumption(diagnostics: File): Int {

    var rowCount = 0
    val bitCount = mutableListOf<Int>()

    diagnostics.readLines().forEach {
        val bits = it.toCharArray().map { bit -> bit.digitToInt() }.toIntArray()
        if (bitCount.isEmpty()) {
            bits.forEach { bit -> bitCount.add(bit) }
        }
        else {
            bits.forEachIndexed { index, bit -> bitCount[index] = bitCount[index] + bit }
        }
        rowCount++
    }

    val gammaBits = bitCount.map { if (rowCount - it > rowCount / 2)  0 else 1 }
    val epsilonBits = gammaBits.map { if (it == 1)  0 else 1 }

    val gamma = gammaBits.joinToString(separator = "") { it.toString() }.toInt(2)
    val epsilon = epsilonBits.joinToString(separator = "") { it.toString() }.toInt(2)

    return gamma * epsilon
}

fun calculateLifeSupport(diagnostics: File): Int {

    val diagnosticLines = diagnostics.readLines()
    val oxygen = findLifeSupportParameterValue(diagnosticLines, LifeSupportParameter.OXYGEN)
    val carbonDioxide = findLifeSupportParameterValue(diagnosticLines, LifeSupportParameter.CARBON_DIOXIDE)

    return oxygen * carbonDioxide
}

fun findLifeSupportParameterValue(diagnosticLines: List<String>, parameter: LifeSupportParameter): Int {
    var prefix = ""
    var lifeSupportParameterValue = -1
    while (lifeSupportParameterValue < 0) {
        val ones = diagnosticLines.count { it.startsWith(prefix + "1") }
        val zeroes = diagnosticLines.count { it.startsWith(prefix + "0") }
        prefix += parameter.evaluateBits(ones, zeroes)
        diagnosticLines.singleOrNull { it.startsWith(prefix) }?.let { lifeSupportParameterValue = it.toInt(2) }
    }
    return lifeSupportParameterValue
}

enum class LifeSupportParameter {
    OXYGEN {
        override fun evaluateBits(oneCount: Int, zeroCount: Int): String =  if (oneCount >= zeroCount) "1" else "0"
    },
    CARBON_DIOXIDE {
        override fun evaluateBits(oneCount: Int, zeroCount: Int): String = if (oneCount >= zeroCount) "0" else "1"
    };

    abstract fun evaluateBits(oneCount: Int, zeroCount: Int): String
}