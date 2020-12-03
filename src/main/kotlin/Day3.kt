import java.lang.RuntimeException
import java.math.BigInteger

fun main() {
    val fileName = "/day_3_input.txt"
    val runner = Day3Runner(fileName)
    val slope = Slope(3, 1)
    val treeCount = runner.run(slope)
    val productCount = partDeuxRunner(runner)

    println("Answer to Part I: $treeCount")
    println("Answer to Part Deux: $productCount")
}

data class Slope(val run: Int, val rise: Int)

fun partDeuxRunner(runner: Day3Runner): BigInteger {
    val slopes = listOf(
        Slope(1, 1),
        Slope(3, 1),
        Slope(5, 1),
        Slope(7, 1),
        Slope(1, 2)
    )

    val counts = slopes.map { runner.run(it)}
    return counts.reduce { product, count -> product * count}
}

class Day3Runner(private val fileName: String) {
    private val grid = parseInput()
    private val TREE = "#"

    private fun parseInput(): List<List<String>> {
        val input = this.javaClass.getResource(fileName).readText()
        return input
            .split("\n")
            .filter { it.length > 0 }
            .map { it -> it.split("").filter { it.isNotEmpty() }}
    }

    fun run(slope: Slope): BigInteger {
        var x = 0
        var y = 0

        var count: BigInteger = BigInteger.valueOf(0L)
        try {
            while (y < grid.size) {
                val coord = getCoord(x, y)
                if (coord == TREE) {
                    count++
                }

                y += slope.rise
                x += slope.run
                x = x % grid[0].size
            }

        } catch (exc: RuntimeException) {
            println(exc.message);
        } finally {
            return count
        }
    }

    fun getCoord(x: Int, y: Int): String {
        if (y >= grid.size) {
            throw RuntimeException("over grid")
        }

        return grid[y][x]
    }
}
