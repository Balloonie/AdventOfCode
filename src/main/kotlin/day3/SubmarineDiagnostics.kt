package day3

import loadResource
import java.io.File


fun main(args: Array<String>) {
    val diagnostics = loadResource("day3/Diagnostics")
    println(calculatePowerConsumption(diagnostics))
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