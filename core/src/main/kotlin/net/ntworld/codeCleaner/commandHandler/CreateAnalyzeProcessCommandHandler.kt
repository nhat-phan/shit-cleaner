package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.CLI
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.query.FindProjectByIdQuery
import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.Util
import net.ntworld.codeCleaner.command.CreateCodeQualityCommand
import net.ntworld.codeCleaner.event.AnalyzeProcessStartedEvent
import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.codeCleaner.make
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
            val isRunning = queryBus().process(IsExecutingQuery.make(command.projectId))
            if (isRunning.value) {
                return@infrastructure
            }

            val project = queryBus().process(FindProjectByIdQuery.make(command.projectId))

            val start = Util.utcNow()
            eventBus().publish(AnalyzeProcessStartedEvent.make(projectId = command.projectId, datetime = start))

            val response = serviceBus().process(
                ExecuteRequest.make(
                    project.path, project.id, CLI.codeClimateAnalyze, mapOf()
                )
            )
            // TODO check response error
            val output = response.getResponse().output

            val end = Util.utcNow()
            commandBus().process(
                CreateCodeQualityCommand.make(
                    id = command.projectId,
                    projectId = command.projectId,
                    analyzeProcessStartAt = start,
                    analyzeProcessEndAt = end,
                    analyzeRawOutput = output
                )
            )
            eventBus().publish(
                CodeAnalyzedEvent.make(
                    projectId = command.projectId,
                    codeQualityId = command.projectId
                )
            )
        }
    }
}