package net.ntworld.codeCleaner.language

class TypeScript : ProgrammingLanguage {
    override val name: String = "typescript"

    override val extensions: List<String> = listOf(
        "ts", "tsx"
    )
}