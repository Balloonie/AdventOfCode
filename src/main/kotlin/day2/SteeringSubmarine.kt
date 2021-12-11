package day2

import loadResource
import java.io.File


fun main(args: Array<String>) {
    val steeringInput = loadResource("day2/SteeringInput")
    println(calculateSteeringPoint(steeringInput))
}

fun calculateSteeringPoint(steeringInput: File): Int {

    var horizontal = 0
    var depth = 0

    steeringInput.readLines().forEach {
        val split = it.split(" ")
        when(split[0]) {
            "forward" -> horizontal += split[1].toInt()
            "up" -> depth -= split[1].toInt()
            "down" -> depth += split[1].toInt()
        }
    }
    return horizontal * depth
}
