import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

data class PassField(val field: String, val valid: Boolean)
class Day4KtTest : FunSpec({
    test("handles valid birth year") {
        validateField("byr:1970") shouldBe true
        validateField("byr:2002") shouldBe true
        validateField("byr:1920") shouldBe true
    }

    test("handles invalid birth year") {
        validateField("byr:198") shouldBe false
        validateField("byr:0000") shouldBe false
        validateField("byr:2020") shouldBe false
        validateField("byr:2002") shouldBe true
        validateField("byr:1910") shouldBe false
    }

    test("handles valid issue yr") {
        validateField("iyr:198") shouldBe false
        validateField("iyr:2010") shouldBe true
    }

    test("handles exp yr") {
        validateField("eyr:2015") shouldBe false
        validateField("eyr:2025") shouldBe true
    }

    test("handle height") {
        validateField("hgt:10cm") shouldBe false
        validateField("hgt:160cm") shouldBe true
        validateField("hgt:60in") shouldBe true
    }

    test("hair color") {
        validateField("hcl:#123abc") shouldBe true
        validateField("hcl:#123abz") shouldBe false
        validateField("hcl:#123abz") shouldBe false
    }

    test("eye color") {
        validateField("ecl:brn") shouldBe true
        validateField("ecl:wat") shouldBe false
    }

    test("invalid pasports") {
        val passports = listOf("eyr:1972 cid:100\n" +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926", "iyr:2019\n" +
                "hcl:#602927 eyr:1967 hgt:170cm\n" +
                "ecl:grn pid:012533040 byr:1946", "hcl:dab227 iyr:2012\n" +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277", "hgt:59cm ecl:zzz\n" +
                "eyr:2038 hcl:74454a iyr:2023\n" +
                "pid:3556412378 byr:2007")

        passports.any { validatePassport(it) } shouldBe false
    }

    test("valid passports") {
        val passports = listOf("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                    "hcl:#623a2f", "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                    "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm", "hcl:#888785\n" +
                "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                "pid:545766238 ecl:hzl\n" +
                "eyr:2022", "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719\n"
        )

        passports.all { validatePassport(it) } shouldBe true
    }

    test("pid") {
        validateField("pid:000000001") shouldBe true
        validateField("pid:0123456789") shouldBe false
    }
})