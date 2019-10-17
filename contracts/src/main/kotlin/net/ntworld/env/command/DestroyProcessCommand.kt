package net.ntworld.env.command

import net.ntworld.foundation.cqrs.Command

interface DestroyProcessCommand: Command {
    val id: String

    companion object
}