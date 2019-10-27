package net.ntworld.codeCleaner.statistic.internal

import net.ntworld.codeCleaner.statistic.CodeStatisticData

data class CodeStatisticDataImpl(
    override val total: Long,
    override val languages: List<CodeStatisticData.LanguageData>
) : CodeStatisticData