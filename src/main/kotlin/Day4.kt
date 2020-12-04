fun main() {
    println("hey there")
    val runner = Day4Runner("day_4_input.txt")
    val ans = runner.run()
    val ans2 = runner.run2()
    println("Answer to Part I: $ans")
    println("Answer to Part Deux: $ans2")
}

const val BIRTH_YEAR = "byr"
const val ISSUE_YEAR = "iyr"
const val EXPIRATION_YEAR = "eyr"
const val HEIGHT = "hgt"
const val HAIR_COLOR = "hcl"
const val EYE_COLOR = "ecl"
const val PASSPORT_ID = "pid"
const val COUNTRY_ID = "cid"

fun validatePassport(passport: String): Boolean {
    return passport.split("\\s".toRegex())
        .filter { it.length > 0 }
        .all { validateField(it) }
}

fun validateField(field: String): Boolean {
    val bits = field.split(":")
    val key = bits.first()
    val value = bits.last()
    return when (key) {
        BIRTH_YEAR -> (1920..2002).contains(value.toInt())
        ISSUE_YEAR -> (2010..2020).contains((value.toInt()))
        EXPIRATION_YEAR -> (2020..2030).contains(value.toInt())
        HAIR_COLOR -> "^#[0-9a-f]{6}$".toRegex().containsMatchIn(value)
        EYE_COLOR -> "amb|blu|brn|gry|grn|hzl|oth".toRegex().containsMatchIn(value)
        PASSPORT_ID -> "^[0-9]{9}$".toRegex().containsMatchIn(value)
        COUNTRY_ID -> true
        HEIGHT -> {
            var pat = "\\d+".toRegex()
            var matches = pat.find(value) ?: return false

            val height = matches.value.toInt()

            val unit = "\\D+".toRegex().find(value) ?: return false

            return when (unit.value) {
                "cm" -> (150..193).contains(height)
                "in" -> (59..76).contains(height)
                else -> false
            }
        }
        else -> false
    }
}

class Day4Runner(fileName: String) {
    private val passports = parseInput(fileName)

    fun parseInput(fileName: String): List<String> {
        val input = this.javaClass.getResource(fileName).readText()

        val passports: MutableList<String> = ArrayList()
        var passport = "";
        for (line in input.lineSequence()) {
            if (line.isBlank()) {
                passports.add(passport)
                passport = ""
            } else {
                passport = "$passport $line"
            }
        }

        return passports
    }

    fun validate(passport: String): Boolean {
        return passport.contains(BIRTH_YEAR) &&
                passport.contains(ISSUE_YEAR) &&
                passport.contains(EXPIRATION_YEAR) &&
                passport.contains(HEIGHT) &&
                passport.contains(HAIR_COLOR) &&
                passport.contains(EYE_COLOR) &&
                passport.contains(PASSPORT_ID)
    }

    fun run(): Int {
        return passports.filter { validate(it) }
            .size
    }

    fun run2(): Int {
        return passports.filter { validatePassport(it) }
            .filter { validate(it) }
            .size
    }
}