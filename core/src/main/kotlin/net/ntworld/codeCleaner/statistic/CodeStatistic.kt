package net.ntworld.codeCleaner.statistic

import net.ntworld.codeCleaner.language.ProgrammingLanguage
import net.ntworld.codeCleaner.statistic.internal.CodeStatisticDataImpl
import net.ntworld.codeCleaner.statistic.internal.CodeStatisticLanguageDataImpl

class CodeStatistic {
    data class Item(
        val path: String,
        val lines: Int,
        val language: ProgrammingLanguage
    )

    private val data = mutableMapOf<String, Item>()

    fun collect(path: String, lines: Int, language: ProgrammingLanguage) {
        data[path] = Item(path = path, lines = lines, language = language)
    }

    fun buildData(): CodeStatisticData {
        val languages = mutableMapOf<ProgrammingLanguage, Long>()
        val total: Long = data.values.fold(0L) { memo, item ->
            if (null === languages[item.language]) {
                languages[item.language] = 0L
            }
            languages[item.language] = languages[item.language]!! + item.lines
            memo + item.lines
        }
        return CodeStatisticDataImpl(
            total = total,
            languages = languages.map {
                CodeStatisticLanguageDataImpl(
                    language = it.key.displayName,
                    lines = it.value,
                    percent = (it.value.toFloat() / total) * 100f
                )
            }
        )
    }

}