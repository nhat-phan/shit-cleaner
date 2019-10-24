package net.ntworld.codeCleaner.language

class PHP : ProgrammingLanguage {
    override val name: String = "php"

    override val extensions: List<String> = listOf(
        "php", "phtml", "php5", "php7", "phps", "php-s", "pht"
    )
}