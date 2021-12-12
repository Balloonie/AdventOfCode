package day6

import loadResource
import java.io.File

fun main(args: Array<String>) {
    val initialPopulation = createPopulation(loadResource("day6/LanternfishesState"))
    val population = simulatePopulation(initialPopulation, 80)
    println(population.size)
}

fun simulatePopulation(initialPopulation: List<Int>, days: Int): List<Int> {
    val population = initialPopulation.toMutableList()
    var spawn = 0
    for (i in 1..days) {
        for ((index, pop) in  population.withIndex()) {
            if (pop == 0) spawn++
            population[index] = if (pop == 0) 6 else pop - 1
        }
        for (i in 1..spawn) {
            population.add(8)
        }
        spawn = 0
    }
    return population
}

fun createPopulation(data: File): List<Int> = data.readLines().flatMap { it.split(",").map { p -> p.toInt() } }

