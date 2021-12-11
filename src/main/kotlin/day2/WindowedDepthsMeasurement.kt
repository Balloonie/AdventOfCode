package day2

import java.io.File
import java.nio.file.Paths
import java.util.*

val resourcesPath = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\day2\\"

fun main(args: Array<String>) {
    val measurements = loadMeasurements(resourcesPath + "Measurements")

    println(countIncreasingDepth(measurements))
}

fun countIncreasingDepth(depthsMeasurement: List<Int>): Int {
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

fun loadMeasurements(pathname: String): List<Int> = File(pathname).readLines().map { it.toInt()}.toList()

