package day1

import loadResource

fun main(args: Array<String>) {
    val measurements = loadResource("day1/MeasurementsSimpleDepths")
    println(countIncreasingDepth(measurements))
}

fun countIncreasingDepth(depthsMeasurement: List<Int>): Int {
    var incrementCounter = 0
    var lastMeasurment = -1
    for ((index, currentMeasurement) in depthsMeasurement.withIndex()) {
        if (index != 0) {
            if (lastMeasurment < currentMeasurement) {
                incrementCounter++
            }
        }
        lastMeasurment = currentMeasurement
    }
    return incrementCounter
}