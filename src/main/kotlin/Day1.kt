fun main() {
    val day = Day1()
    val answer = day.run()
    val answer2 = day.run2()
    println("The answer to part I is: $answer")
    println("the answer to part II is is: $answer2")
}

class Day1() {
    private val nums = parseInput()

    fun parseInput(): List<Int> {

        val input = this.javaClass.getResource("/day_1_input.txt").readText()
        return input
            .split("\n")
            .filter { it.length > 0}
            .map { it.toInt() }

    }

    fun run2(): Int {
        val numSet = nums.toSet()

        for ((idx, value) in nums.withIndex()) {
            val remainder = nums.subList(idx + 1, nums.size)
            for (diff in remainder) {
                val last = 2020 - value - diff
                if (numSet.contains(last)) {
                    return last * value * diff
                }
            }
        }

        return 0
    }

    fun run(): Int {
        val numSet = nums.toSet()
        var found = -1

        val it = nums.iterator()
        while (it.hasNext() && found < 0) {
            val num = it.next()
            val diff = 2020 - num
            if (numSet.contains(diff)) {
                found = diff
            }
        }
        return found * (2020 - found)
    }
}