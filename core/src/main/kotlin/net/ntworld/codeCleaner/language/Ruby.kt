package net.ntworld.codeCleaner.language

class Ruby : ProgrammingLanguage {
    override val name: String = "ruby"

    override val displayName: String = "Ruby"

    override val extensions: List<String> = listOf(
        "rb"
    )
}