package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.ProjectManager
import net.ntworld.codeCleaner.command.CreateProjectCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateProjectCommandHandler : CommandHandler<CreateProjectCommand> {

    override fun handle(command: CreateProjectCommand) {
        ProjectManager.create(
            id = command.id,
            name = command.name,
            path = command.path
        )
    }

}