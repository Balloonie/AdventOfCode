package year2023.day2

import loadResource

fun main(args: Array<String>) {
    val games = loadResource("year2023/day2/BagGameInput").readLines()
    println(possibleGameSum(games))
    println(gamePoweSum(games))
}

fun possibleGameSum(games: List<String>): Int {
    return games.sumOf {
        if (getHighestNumber(it, "red") <= 12 &&
            getHighestNumber(it, "green") <= 13 &&
            getHighestNumber(it, "blue") <= 14) {
            getGame(it)
        }
        else 0
    }
}

fun gamePoweSum(games: List<String>): Int {
    return games.sumOf {
        val red = getHighestNumber(it, "red")
        val green = getHighestNumber(it, "green")
        val blue = getHighestNumber(it, "blue")
        red * green * blue
    }
}

fun getGame(game: String) = Regex("\\d+").find(game)!!.value.toInt()


fun getHighestNumber(game: String, color: String) : Int{
    val regex = Regex("\\d+ $color")
    val matches = regex.findAll(game);
    return matches.map { it.groupValues[0].split(" ")[0].toInt() }.maxOrNull() ?: -1
}
