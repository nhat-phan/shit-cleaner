package net.ntworld.codeCleaner.language

class Scala : ProgrammingLanguage {
    override val name: String = "scala"

    override val displayName: String = "Scala"

    override val extensions: List<String> = listOf(
        "scala", "sc"
    )
}