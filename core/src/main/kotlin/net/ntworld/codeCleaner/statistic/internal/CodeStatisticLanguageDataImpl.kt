package net.ntworld.codeCleaner.statistic.internal

import net.ntworld.codeCleaner.statistic.CodeStatisticData

data class CodeStatisticLanguageDataImpl(
    override val language: String,
    override val lines: Long,
    override val percent: Float
) : CodeStatisticData.LanguageData