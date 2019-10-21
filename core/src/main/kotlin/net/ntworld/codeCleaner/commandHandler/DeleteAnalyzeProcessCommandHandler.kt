package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class DeleteAnalyzeProcessCommandHandler : CommandHandler<DeleteAnalyzeProcessCommand> {

    override fun handle(command: DeleteAnalyzeProcessCommand) {
    }

}