package day7

import loadResource
import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {
    val startPositions = createStartingPositions(loadResource("day7/CrabAlignment"))
    println(calculateOptimalAlignmentFuelCost(startPositions))

}

fun calculateOptimalAlignmentFuelCost(startPositions: List<Int>): Int {
    var max = 0
    var min = Int.MAX_VALUE

    for (startPosition in startPositions) {
        if (startPosition > max) max = startPosition
        if (startPosition < min) min = startPosition
    }

    var fuel = Int.MAX_VALUE

    for (targetPosition in min..max) {
        val fuelCost = calculateFuelCost(startPositions, targetPosition)
        if (fuelCost < fuel) fuel = fuelCost
        println("Target: $targetPosition, Fuelcost: $fuelCost")
    }

    return fuel
}

fun calculateFuelCost(startPositions: List<Int>, targetPosition: Int): Int = startPositions.sumOf { fuelConsumption(abs(targetPosition - it)) }

fun fuelConsumption(steps: Int): Int = (0..steps).sum()

fun createStartingPositions(data: File): List<Int> = data.readLines().flatMap { it.split(",").map { p -> p.toInt() } }