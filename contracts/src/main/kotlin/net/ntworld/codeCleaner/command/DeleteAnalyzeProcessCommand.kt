package net.ntworld.codeCleaner.command

import net.ntworld.foundation.cqrs.Command

interface DeleteAnalyzeProcessCommand : Command {
    val projectId: String

    companion object
}
