package day1

import loadResource
import java.util.Queue
import java.util.LinkedList

fun main(args: Array<String>) {
    val measurements = loadResource("day1/MeasurementsWindowedDepths").readLines().map { it.toInt()}.toList()
    println(countIncreasingWindowedDepth(measurements))
}

fun countIncreasingWindowedDepth(depthsMeasurement: List<Int>): Int {
    var incrementCounter = 0
    val measurementTriple: Queue<Int> = LinkedList()
    for (currentMeasurement in depthsMeasurement) {
        if (measurementTriple.size < 3) {
            measurementTriple.add(currentMeasurement)
        }
        else {
            val last = measurementTriple.sum()
            measurementTriple.remove()
            measurementTriple.add(currentMeasurement)
            if (last < measurementTriple.sum()) {
                incrementCounter++
            }
        }
    }
    return incrementCounter
}