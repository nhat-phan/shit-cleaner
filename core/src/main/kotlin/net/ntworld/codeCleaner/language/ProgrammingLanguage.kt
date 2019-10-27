package net.ntworld.codeCleaner.language

interface ProgrammingLanguage {
    val name: String

    val displayName: String

    val extensions: List<String>
}