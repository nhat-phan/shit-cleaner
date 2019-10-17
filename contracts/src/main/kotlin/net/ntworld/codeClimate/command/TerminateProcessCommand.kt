package net.ntworld.codeClimate.command

import net.ntworld.foundation.cqrs.Command

interface TerminateProcessCommand : Command {
    val watchId: String

    companion object
}