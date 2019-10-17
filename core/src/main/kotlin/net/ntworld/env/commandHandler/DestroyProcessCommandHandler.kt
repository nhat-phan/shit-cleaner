package net.ntworld.env.commandHandler

import net.ntworld.env.ExecuteWatchdogManager
import net.ntworld.env.command.DestroyProcessCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class DestroyProcessCommandHandler : CommandHandler<DestroyProcessCommand> {

    override fun handle(command: DestroyProcessCommand) {
        ExecuteWatchdogManager.destroyProcessWatchdog(command.id)
    }

}