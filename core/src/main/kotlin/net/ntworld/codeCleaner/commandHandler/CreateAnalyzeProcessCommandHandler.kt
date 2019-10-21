package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateAnalyzeProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<CreateAnalyzeProcessCommand> {

    override fun handle(command: CreateAnalyzeProcessCommand) {
        TODO()
    }

}