package net.ntworld.env.command

import net.ntworld.foundation.cqrs.Command

interface MakeExecuteWatchdogCommand: Command {
    val id: String

    val timeout: Long

    companion object
}