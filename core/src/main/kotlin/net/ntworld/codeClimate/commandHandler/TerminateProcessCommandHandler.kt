package net.ntworld.codeClimate.commandHandler

import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.codeClimate.command.TerminateProcessCommand
import net.ntworld.codeClimate.make
import net.ntworld.env.command.DestroyProcessCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class TerminateProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<TerminateProcessCommand> {

    override fun handle(command: TerminateProcessCommand) {
        infrastructure.commandBus().process(
            DestroyProcessCommand.make(command.watchId)
        )
    }
    
}