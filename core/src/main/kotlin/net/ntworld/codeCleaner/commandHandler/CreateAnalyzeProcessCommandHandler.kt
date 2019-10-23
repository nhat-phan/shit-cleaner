package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.CLI
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.query.FindProjectByIdQuery
import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.event.AnalyzeProcessStartedEvent
import net.ntworld.codeCleaner.event.AnalyzeProcessStoppedEvent
import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.codeCleaner.make
import net.ntworld.env.command.MakeExecuteWatchdogCommand
import net.ntworld.env.query.IsExecutingQuery
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.foundation.Handler
import net.ntworld.foundation.Use
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateAnalyzeProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<CreateAnalyzeProcessCommand> {

    @Use(contract = AnalyzeProcessStartedEvent::class)
    override fun handle(command: CreateAnalyzeProcessCommand) {
        // TODO
        infrastructure {
            val isRunning = infrastructure.queryBus().process(IsExecutingQuery.make(command.projectId))
            if (isRunning.value) {
                return@infrastructure
            }

            val project = queryBus().process(FindProjectByIdQuery.make(command.projectId))

            commandBus().process(MakeExecuteWatchdogCommand.make(id = command.projectId, timeout = -1))

            eventBus().publish(AnalyzeProcessStartedEvent.make(projectId = command.projectId))
            val response = serviceBus().process(
                ExecuteRequest.make(project.path, project.id, CLI.codeClimateAnalyze, mapOf())
            )

            // TODO check response error
            eventBus().publish(
                CodeAnalyzedEvent.make(
                    projectId = command.projectId,
                    raw = response.getResponse().output
                )
            )
        }
    }

}