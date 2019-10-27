package net.ntworld.codeCleaner.language

class Python : ProgrammingLanguage {
    override val name: String = "python"

    override val displayName: String = "Python"

    override val extensions: List<String> = listOf(
        "py", "pyi", "pyc", "pyd", "pyo", "pyw", "pyz"
    )
}