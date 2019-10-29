package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.CodeQualityManager
import net.ntworld.codeCleaner.command.CreateCodeQualityCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateCodeQualityCommandHandler : CommandHandler<CreateCodeQualityCommand> {
    override fun handle(command: CreateCodeQualityCommand) {
        CodeQualityManager.create(
            id = command.id,
            projectId = command.projectId,
            analyzeProcessStartAt = command.analyzeProcessStartAt,
            analyzeProcessEndAt = command.analyzeProcessEndAt,
            raw = command.analyzeRawOutput
        )
    }
}