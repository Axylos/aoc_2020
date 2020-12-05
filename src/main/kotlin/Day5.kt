import java.lang.RuntimeException

fun main() {
    val runner = Day5Runner("day_5_input.txt")
    val ans = runner.run()
    val ans2 = runner.run2()

    println("the answer to part I: $ans")
    println("The answer to Part Deux: $ans2")
}

class SeatSearch(var start: Int = 0, var end: Int = 100) {
    fun move(dir: String) {
        val mid = (start + end) / 2
        when (dir) {
            "L", "F" -> {
                end = mid
            }
            "B", "R" -> {
                start = mid + 1
            }
        }
    }
}

fun search(boardingPass: String): Int {
    val rowSearcher = SeatSearch(0, 127)
    boardingPass.substring(0, 7).forEach { rowSearcher.move(it.toString()) }

    val row = rowSearcher.start

    val seatSearcher = SeatSearch(0, 7)
    boardingPass.substring(7, boardingPass.length).forEach { seatSearcher.move(it.toString()) }

    val seat = seatSearcher.start

    return (row * 8) + seat
}

class Day5Runner(fileName: String) {
    private val boardingPasses = parseInput(fileName)

    fun parseInput(fileName: String): List<String> {
        val input = this.javaClass.getResource(fileName).readText()
            .split("\n")
            .filter { it.isNotEmpty() }

        return input
    }

    fun run(): Int {
       val ids = boardingPasses.map { search(it) }

        return ids.maxOf { it }
    }

    fun run2(): Int {
        val ids = boardingPasses.map { search(it) }
            .sorted()

        ids.forEachIndexed { idx, n ->
            run {
                if ((idx + 1 < ids.size) && ids[idx + 1] == n + 2) {
                    return n + 1
                }
            }
        }

        return -1
    }
}