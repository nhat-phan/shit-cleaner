package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.event.AnalyzeProcessStoppedEvent
import net.ntworld.codeCleaner.make
import net.ntworld.env.command.DestroyProcessCommand
import net.ntworld.env.query.IsExecutingQuery
import net.ntworld.foundation.Handler
import net.ntworld.foundation.Use
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class DeleteAnalyzeProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<DeleteAnalyzeProcessCommand> {

    @Use(contract = AnalyzeProcessStoppedEvent::class)
    override fun handle(command: DeleteAnalyzeProcessCommand) {
        // TODO
        infrastructure {
            val isRunning = infrastructure.queryBus().process(IsExecutingQuery.make(command.projectId))
            if (!isRunning.value) {
                return@infrastructure
            }

            commandBus().process(
                DestroyProcessCommand.make(id = command.projectId)
            )
            eventBus().publish(AnalyzeProcessStoppedEvent.make(projectId = command.projectId))
        }
    }

}