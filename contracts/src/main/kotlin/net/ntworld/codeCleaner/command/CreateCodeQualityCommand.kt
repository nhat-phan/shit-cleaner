package net.ntworld.codeCleaner.command

import net.ntworld.foundation.cqrs.Command

interface CreateCodeQualityCommand : Command {
    val id: String

    val projectId: String

    val analyzeRawOutput: String

    val analyzeProcessStartAt: String

    val analyzeProcessEndAt: String

    companion object
}