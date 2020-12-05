import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Assertions.*

class Day5KtTest : FunSpec ({
    test("seat resolver") {
        search("BFFFBBFRRR") shouldBeExactly 567
        search("FFFBBBFRRR") shouldBeExactly  119
        search("BBFFBBFRLL") shouldBeExactly  820
    }
})

