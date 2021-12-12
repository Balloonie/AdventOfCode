package day5

import loadResource
import java.io.File

fun main(args: Array<String>) {

    val ventCoordinates = loadResource("day5/VentCoordinates")
    println(countOverlappingSpots(ventCoordinates))

}

fun countOverlappingSpots(ventCoordinates: File): Int {
    val ventLines = getVentLines(ventCoordinates)
    val intersectionPoints = mutableSetOf<Coordinate>()
    var intersectionCount = 0
    for ((index, ventLine) in ventLines.withIndex()) {
        if (index != ventLines.size - 1) {
            for (i in (index + 1) toward (ventLines.size - 1)) {
                val intersections = ventLine.getIntersections(ventLines[i])
                for (intersection in intersections) {
                    if (intersectionPoints.add(intersection)) intersectionCount++
                }
            }
        }
    }

    return intersectionCount
}

fun getVentLines(ventCoordinates: File): List<Line> {
    return ventCoordinates.readLines().map {
        val coordinates = it.split(" -> ")
        Line(Coordinate(coordinates[0].split(",")[0].toInt(), coordinates[0].split(",")[1].toInt()), Coordinate(coordinates[1].split(",")[0].toInt(), coordinates[1].split(",")[1].toInt()))
    }.filter { it.isHorizontalOrVertical() }.toList()
}


class Line(private val start: Coordinate, private val end: Coordinate) {

    private val range = mutableSetOf<Coordinate>()
    init {
        if (start.x == end.x) {
            for (i in start.y toward end.y) {
                range.add(Coordinate(start.x, i))
            }
        }
        else if (start.y == end.y) {
            for (i in start.x toward end.x) {
                range.add(Coordinate(i, start.y))
            }
        }
    }

    fun getIntersections(line: Line): List<Coordinate> {
        val intersections = mutableListOf<Coordinate>()
        for (coordinate in range) {
            if (line.range.contains(coordinate)) intersections.add(coordinate.copy())
        }
        return intersections
    }


    fun isHorizontalOrVertical(): Boolean {
        return start.x == end.x || start.y == end.y
    }

    override fun toString(): String {
        return "Line(start=$start, end=$end)"
    }
}

data class Coordinate(val x: Int, val y: Int)

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) - 1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}