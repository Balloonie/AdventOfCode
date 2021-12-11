package day1

import java.io.File
import java.nio.file.Paths

val resourcesPath = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\day1\\"

fun main(args: Array<String>) {
    val measurements = loadMeasurements(resourcesPath + "Measurements")
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

fun loadMeasurements(pathname: String): List<Int> = File(pathname).readLines().map { it.toInt()}.toList()

