package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.make
import net.ntworld.env.command.DestroyProcessCommand
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class DeleteAnalyzeProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<DeleteAnalyzeProcessCommand> {

    override fun handle(command: DeleteAnalyzeProcessCommand) {
        // TODO
        infrastructure {
            val state = queryBus().process(FindProjectStateByIdQuery.make(command.projectId))
            if (!state.isRunning) {
                return@infrastructure
            }

            commandBus().process(
                DestroyProcessCommand.make(id = command.projectId)
            )
        }
    }

}