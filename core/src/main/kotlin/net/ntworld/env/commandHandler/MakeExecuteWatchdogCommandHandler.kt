package net.ntworld.env.commandHandler

import net.ntworld.env.ExecuteWatchdogManager
import net.ntworld.env.command.MakeExecuteWatchdogCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class MakeExecuteWatchdogCommandHandler : CommandHandler<MakeExecuteWatchdogCommand> {
    override fun handle(command: MakeExecuteWatchdogCommand) {
        ExecuteWatchdogManager.makeWatchdog(command.id, command.timeout)
    }
}