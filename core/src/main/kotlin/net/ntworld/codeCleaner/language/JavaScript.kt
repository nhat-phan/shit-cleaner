package net.ntworld.codeCleaner.language

class JavaScript : ProgrammingLanguage {
    override val name: String = "javascript"

    override val extensions: List<String> = listOf(
        "js", "mjs", "jsx"
    )
}