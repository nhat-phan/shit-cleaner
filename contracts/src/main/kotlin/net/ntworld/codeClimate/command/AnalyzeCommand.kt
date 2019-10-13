package net.ntworld.codeClimate.command

import net.ntworld.codeClimate.structure.Engine
import net.ntworld.foundation.cqrs.Command

interface AnalyzeCommand: Command {
    val basePath: String

    val path: List<String>?

    val engine: List<Engine>?

    companion object
}
