package net.ntworld.codeCleaner.quality

import net.ntworld.codeCleaner.codeClimate.Lines
import net.ntworld.codeCleaner.codeClimate.Location
import net.ntworld.codeCleaner.codeClimate.internal.LinesImpl
import net.ntworld.codeCleaner.codeClimate.internal.LocationImpl
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import kotlin.test.Test
import kotlin.test.assertEquals

class CodeQualityParserTest {

    @Test
    fun testParse() {
        val raw = CodeQualityParserTest::class.java.getResource(
            "/analysis-results/foundation-v0.5.1.json"
        ).readText()

        val (codeSmells, duplications) = CodeQualityParser.parse(raw)
        assertEquals(55, codeSmells.size)
        assertEquals(256, duplications.size)
    }

    @Test
    fun testCountNumberOfLines() {
        data class Item(val path: String, val lines: Lines, val locations: List<Location>, val result: Int)

        val dataset = listOf(
            Item(
                path = "test.kt",
                lines = LinesImpl(begin = 10, end = 18),
                locations = listOf(),
                result = 9
            ),
            Item(
                path = "test.kt",
                lines = LinesImpl(begin = 10, end = 18),
                locations = listOf(
                    LocationImpl(path = "abc.kt", lines = LinesImpl(begin = 5, end = 10))
                ),
                result = 9
            ),
            Item(
                path = "test.kt",
                lines = LinesImpl(begin = 10, end = 18),
                locations = listOf(
                    LocationImpl(path = "abc.kt", lines = LinesImpl(begin = 1, end = 4)),
                    LocationImpl(path = "test.kt", lines = LinesImpl(begin = 5, end = 7)),
                    LocationImpl(path = "test.kt", lines = LinesImpl(begin = 25, end = 30))
                ),
                result = 18
            )
        )
        for (item in dataset) {
            assertEquals(item.result, CodeQualityParser.countNumberOfLines(item.path, item.lines, item.locations))
        }
    }

    @Test
    fun testRate() {
        val dataset = mapOf(
            20000 to MaintainabilityRate.Good,
            200000 to MaintainabilityRate.Good,
            499999 to MaintainabilityRate.Good,
            500000 to MaintainabilityRate.Moderate,
            1000000 to MaintainabilityRate.Moderate,
            1999999 to MaintainabilityRate.Moderate,
            2000000 to MaintainabilityRate.Bad,
            21000000 to MaintainabilityRate.Bad
        )
        dataset.forEach { (point, rate) ->
            assertEquals(rate, CodeQualityParser.rate(point))
        }
    }

    @Test
    fun testRateFile() {
        val dataset = mapOf(
            20000 to MaintainabilityRate.Good,
            200000 to MaintainabilityRate.Good,
            1000000 to MaintainabilityRate.Good,
            1999999 to MaintainabilityRate.Good,
            2000000 to MaintainabilityRate.Good,
            3999999 to MaintainabilityRate.Good,
            4000000 to MaintainabilityRate.Moderate,
            7999999 to MaintainabilityRate.Moderate,
            8000000 to MaintainabilityRate.Moderate,
            15999999 to MaintainabilityRate.Moderate,
            16000000 to MaintainabilityRate.Bad,
            20000000 to MaintainabilityRate.Bad
        )
        dataset.forEach { (point, rate) ->
            assertEquals(rate, CodeQualityParser.rateFile(point))
        }
    }

}