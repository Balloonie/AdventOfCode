package day4

import loadResource

fun main(args: Array<String>) {
    val input = loadResource("day4/Bingo").readLines()
    val sequence = loadSequence(input)
    val boards = loadBoards(input)

    println(findWinningBoardPoints(sequence, boards))
    println(findLosingBoardPoints(sequence, boards))
}

fun findWinningBoardPoints(sequence: List<Int>, boards: List<BingoBoard>): Int {
    var winningBoard = -1
    var winningRounds = Int.MAX_VALUE
    for ((index, board) in boards.withIndex()) {
        val rounds = board.calculateRoundsToWin(sequence)
        if (winningRounds > rounds) {
            winningRounds = rounds
            winningBoard = index
        }
    }
    return boards[winningBoard].calculatePoints(sequence)
}

fun findLosingBoardPoints(sequence: List<Int>, boards: List<BingoBoard>): Int {
    var losingBoard = -1
    var losingRounds = 0
    for ((index, board) in boards.withIndex()) {
        val rounds = board.calculateRoundsToWin(sequence)
        if (losingRounds < rounds) {
            losingRounds = rounds
            losingBoard = index
        }
    }
    return boards[losingBoard].calculatePoints(sequence)
}

fun loadSequence(input: List<String>): List<Int> = input.first().split(",").map { it.toInt() }.toList()

fun loadBoards(input: List<String>): List<BingoBoard> {
    var boardNumbers = mutableListOf<Int>()
    val boards = mutableListOf<BingoBoard>()

    input.forEach {
        if (it.length == 14) {
            boardNumbers.addAll(it.split(" ").filter{ value -> value != "" }.map { value -> value.toInt() })
        }
        if (boardNumbers.size == 25) {
            boards.add(BingoBoard(boardNumbers))
            boardNumbers = mutableListOf()
        }
    }
    return boards
}

class BingoBoard(private val boardNumbers: List<Int>) {

    fun calculateRoundsToWin(sequence: List<Int>): Int {
        var round = 0
        val indices = mutableSetOf<Int>()

        for (number in sequence) {
            indices.add(boardNumbers.indexOf(number))
            round++
            if (bingo(indices)) {
                break
            }
        }

        return round
    }

    fun calculatePoints(sequence: List<Int>): Int {
        var points = 0
        val indices = mutableSetOf<Int>()

        for (number in sequence) {
            indices.add(boardNumbers.indexOf(number))
            if (bingo(indices)) {
                break
            }
        }

        for ((index, boardNumber) in boardNumbers.withIndex()) {
            if (!indices.contains(index)) {
                points += boardNumber
            }
        }

        return points * boardNumbers[indices.last()]
    }

    private fun bingo(markedValues: Set<Int>): Boolean {
        return markedValues.containsAll(listOf(0,1,2,3,4)) ||
               markedValues.containsAll(listOf(5,6,7,8,9)) ||
               markedValues.containsAll(listOf(10,11,12,13,14)) ||
               markedValues.containsAll(listOf(15,16,17,18,19)) ||
               markedValues.containsAll(listOf(20,21,22,23,24)) ||
               markedValues.containsAll(listOf(0,5,10,15,20)) ||
               markedValues.containsAll(listOf(1,6,11,16,21)) ||
               markedValues.containsAll(listOf(2,7,12,17,22)) ||
               markedValues.containsAll(listOf(3,8,13,18,23)) ||
               markedValues.containsAll(listOf(4,9,14,19,24))
    }

    override fun toString(): String {
        return "$boardNumbers"
    }


}