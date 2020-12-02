import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PasswordTest : DescribeSpec({
    describe("Password") {
        it("should handle a valid case") {

            val pass = parseLine("2-3 a: abcdae")
            pass.isValid() shouldBe true
        }

        it("handles stronger range") {
            val pass = parseLine("6-12 f: mqcccdhxfbrhfpffffffffffffffffffffffffff")
            pass.isValid() shouldBe false
        }

        it("should handle an invalid case") {
            val pass = parseLine("1-3 b: cdefg")
            pass.isValid() shouldBe false
        }

        it("handles a case of too many letters") {
            val pass = parseLine("1-3 c: ccdccccc")
            pass.isValid() shouldBe false
        }
    }

    describe("positional validity") {
        it("handles a valid case") {
            val pass = parseLine("1-3 a: abcde")
            pass.isPosValid() shouldBe true
        }

        it("handles an invalid case") {
            val pass = parseLine("1-3 b: cdefg")
            pass.isPosValid() shouldBe false
        }

        it("handles another invalid") {
            parseLine("2-9 c: ccccccccc").isPosValid() shouldBe false
        }
    }
})