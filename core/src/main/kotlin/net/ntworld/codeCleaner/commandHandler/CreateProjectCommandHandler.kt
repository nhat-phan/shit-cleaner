package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.command.CreateProjectCommand
import net.ntworld.codeCleaner.structure.Project
import net.ntworld.codeClimate.make
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateProjectCommandHandler : CommandHandler<CreateProjectCommand> {
    private val data: MutableMap<String, Project> = mutableMapOf()

    override fun handle(command: CreateProjectCommand) {
        data[command.id] = Project.make(
            id = command.id,
            name = command.name,
            path = command.path
        )
    }

}