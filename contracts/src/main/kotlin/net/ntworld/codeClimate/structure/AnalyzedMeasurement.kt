package net.ntworld.codeClimate.structure

interface AnalyzedMeasurement: AnalyzedResult {
    val name: String

    val value: Int

    val engineName: String
}