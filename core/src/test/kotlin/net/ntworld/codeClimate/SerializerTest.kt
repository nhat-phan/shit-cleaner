package net.ntworld.codeClimate

import kotlin.test.Test
import kotlin.test.assertEquals

class SerializerTest {

    @Test
    fun `test parse`() {
        val input = SerializerTest::class.java.getResource("/analyzed-results/foundation-v0.5.1.json").readText()
        val result = Serializer.parse(input)

        assertEquals(315, result.size)
    }

}