package net.ntworld.codeCleaner.statistic

interface CodeStatisticData {
    val total: Long

    val languages: List<LanguageData>

    interface LanguageData {
        val language: String

        val lines: Long

        val percent: Float
    }
}