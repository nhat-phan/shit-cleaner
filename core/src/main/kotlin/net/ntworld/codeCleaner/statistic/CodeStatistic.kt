package net.ntworld.codeCleaner.statistic

import net.ntworld.codeCleaner.language.ProgrammingLanguage

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

}