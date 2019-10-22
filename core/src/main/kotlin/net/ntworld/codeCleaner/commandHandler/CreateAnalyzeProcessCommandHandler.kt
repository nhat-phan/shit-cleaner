package net.ntworld.codeCleaner.commandHandler

import net.ntworld.codeCleaner.CLI
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.query.FindProjectByIdQuery
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.codeCleaner.CoreInfrastructure
import net.ntworld.codeCleaner.make
import net.ntworld.env.command.MakeExecuteWatchdogCommand
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class CreateAnalyzeProcessCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<CreateAnalyzeProcessCommand> {

    override fun handle(command: CreateAnalyzeProcessCommand) {
        // TODO
        infrastructure {
            val state = queryBus().process(FindProjectStateByIdQuery.make(command.projectId))
            if (state.isRunning) {
                return@infrastructure
            }

            val project = queryBus().process(FindProjectByIdQuery.make(command.projectId))

            commandBus().process(
                MakeExecuteWatchdogCommand.make(id = command.projectId, timeout = -1)
            )

            val response = serviceBus().process(
                ExecuteRequest.make(project.path, project.id, CLI.codeClimateAnalyze, mapOf())
            )
            println(response.hasError())
            println(response.getResponse())
        }
    }

}