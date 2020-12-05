import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase.Companion.test
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class SeatSearchTest : FunSpec({
    var instance: SeatSearch = SeatSearch(0, 27)
    beforeTest() {
        instance = SeatSearch(0, 127)
    }
    test("handle directions") {
        instance.move("F")

        instance.start shouldBeExactly 0
        instance.end shouldBeExactly 63

        instance.move("B")

        instance.start shouldBeExactly 32
        instance.end shouldBeExactly 63

        instance.move("F")
        instance.start shouldBeExactly 32
        instance.end shouldBeExactly 47

        instance.move("B")
        instance.move("B")
        instance.move("F")
        instance.move("F")
        instance.start shouldBeExactly 44
        instance.end shouldBeExactly 44
    }
})

