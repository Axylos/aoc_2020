fun main() {
    val runner = Day2Runner()
    runner.run()
}

fun parseLine(line: String): Password {
    val bits = line.split(" ")
    val rangeBit = bits.first()
    val rangeStr = rangeBit.split("-")

    val start = rangeStr.first().toInt()
    val end = rangeStr.last().toInt()

    var pattern = Regex("[a-zA-Z]+")
    var matches = pattern.findAll(line)
    val key = matches.first().value
    val word = matches.last().value

    return Password(word, key, start..end)
}

class Password(word: String, key: String, range: IntRange) {
    private val word = word
    private val key = key
    private val range = range

    fun isValid(): Boolean {
        val count = key.toRegex().findAll(word).count()
        return range.contains(count)
    }

    fun isPosValid(): Boolean {
        val a = range.first
        val b = range.last

        var ctr = 0
        if (word.get(a - 1).toString().equals(key)) {
            ctr++
        }

        if (word.get(b - 1).toString().equals(key)) {
            ctr++
        }

        return ctr == 1
    }
}

class Day2Runner {
    private val lines = parseInput()

    fun parseInput(): List<String> {
        val input = this.javaClass.getResource("/day_2_input.txt").readText()
        return input
            .split("\n")
            .filter { it.length > 0}
    }

    fun run() {
        val parsedLines = lines.map { parseLine(it) }

        val partUnoAnswer = parsedLines
            .filter { it.isValid()}
            .size

        val partDeuxAnswer = parsedLines
            .filter { it.isPosValid() }
            .size

        println("Part Uno Answer: $partUnoAnswer")
        println("PartDeux Answer: $partDeuxAnswer")
    }
}