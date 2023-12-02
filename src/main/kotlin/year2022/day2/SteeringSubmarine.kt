package year2022.day2

import loadResource
import java.io.File


fun main(args: Array<String>) {
    val steeringInput = loadResource("year2022/day2/SteeringInput")
    println("Steering input 2D: " + calculateSteeringPoint2D(steeringInput))

    println("Steering input 3D: " + calculateSteeringPoint3D(steeringInput))
}

fun calculateSteeringPoint3D(steeringInput: File): Int {
    var horizontal = 0
    var depth = 0
    var aim = 0

    steeringInput.readLines().forEach {
        val split = it.split(" ")
        val steeringChangeValue = split[1].toInt()
        when(split[0]) {
            "forward" -> {
                horizontal += steeringChangeValue
                depth += aim * steeringChangeValue
            }
            "up" -> {
                aim -= steeringChangeValue
            }
            "down" ->  {
                aim += steeringChangeValue
            }
        }
    }
    return horizontal * depth
}

fun calculateSteeringPoint2D(steeringInput: File): Int {
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
