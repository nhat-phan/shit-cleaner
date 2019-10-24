package net.ntworld.codeCleaner.codeClimate

import net.ntworld.codeCleaner.language.*

object SupportedLanguages {
    val languages: List<ProgrammingLanguage> = listOf(
        CSharp(),
        GoLang(),
        Java(),
        JavaScript(),
        Kotlin(),
        PHP(),
        Python(),
        Ruby(),
        Scala(),
        Swift(),
        TypeScript()
    )
    val languagesByExtension: Map<String, ProgrammingLanguage> by lazy {
        val result = mutableMapOf<String, ProgrammingLanguage>()
        for (language in languages) {
            language.extensions.forEach {
                result[it] = language
            }
        }
        result
    }

    fun findLanguageByExtension(extension: String) = languagesByExtension[extension]
}