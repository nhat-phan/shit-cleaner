package net.ntworld.codeClimate.command

import net.ntworld.foundation.cqrs.Command

interface AnalyzeCommand: Command {
    val basePath: String

    val path: List<String>?

    companion object
}
