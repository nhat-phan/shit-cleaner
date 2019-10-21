package net.ntworld.codeCleaner.command

import net.ntworld.foundation.cqrs.Command

interface CreateProjectCommand : Command {
    val id: String

    val name: String

    val path: String

    companion object
}
