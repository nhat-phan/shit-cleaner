package net.ntworld.codeCleaner.codeClimate

interface AnalyzedMeasurement: AnalyzedResult {
    val name: String

    val value: Int

    val engineName: String
}