package day6

import loadResource
import java.io.File
import kotlin.math.round

fun main(args: Array<String>) {
    val initialPopulation = createPopulation(loadResource("day6/LanternfishesState"))
    println(initialPopulation.size)
    val population = simulatePopulation(initialPopulation, 256)
    println(population)
}

fun simulatePopulation(initialPopulation: List<Int>, days: Int): Long {
    val generation = (0..8).associateWith { 0L }.toMutableMap()

    for (pop in initialPopulation) {
        generation[pop] = generation[pop]!! + 1L
    }

    for (day in 1..days) {
        val cycleFinished = generation[0]!!
        generation[0] = generation[1]!!
        generation[1] = generation[2]!!
        generation[2] = generation[3]!!
        generation[3] = generation[4]!!
        generation[4] = generation[5]!!
        generation[5] = generation[6]!!
        generation[6] = generation[7]!! + cycleFinished
        generation[7] = generation[8]!!
        generation[8] = cycleFinished
    }

    return generation.values.sum()
}

fun createPopulation(data: File): List<Int> = data.readLines().flatMap { it.split(",").map { p -> p.toInt() } }