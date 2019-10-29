package net.ntworld.codeCleaner.quality

import net.ntworld.codeCleaner.structure.MaintainabilityRate
import kotlin.test.Test
import kotlin.test.assertEquals

class CodeCleanerParserTest {

    @Test
    fun testParse() {
        val raw = CodeCleanerParserTest::class.java.getResource(
            "/analyzed-results/foundation-v0.5.1.json"
        ).readText()

        val (codeSmells, duplications) = CodeQualityParser.parse(raw)
        assertEquals(55, codeSmells.size)
        assertEquals(256, duplications.size)
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