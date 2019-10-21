package net.ntworld.codeCleaner.command

import net.ntworld.foundation.cqrs.Command

interface CreateAnalyzeProcessCommand: Command {
    val projectId: String

    companion object
}
