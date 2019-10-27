package net.ntworld.codeCleaner.language

class TypeScript : ProgrammingLanguage {
    override val name: String = "typescript"

    override val displayName: String = "TypeScript"

    override val extensions: List<String> = listOf(
        "ts", "tsx"
    )
}