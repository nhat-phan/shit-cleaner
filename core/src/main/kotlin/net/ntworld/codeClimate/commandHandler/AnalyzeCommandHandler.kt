package net.ntworld.codeClimate.commandHandler

import net.ntworld.codeClimate.CLI
import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.env.command.MakeExecuteWatchdogCommand
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class AnalyzeCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<AnalyzeCommand> {

    override fun handle(command: AnalyzeCommand) {
        if (null !== command.watchId) {
            infrastructure.commandBus().process(
                MakeExecuteWatchdogCommand.make(
                    id = command.watchId!!, timeout = command.timeout
                )
            )
        }

        val response = infrastructure.serviceBus().process(
            ExecuteRequest.make(command.cwd, command.watchId, CLI.analyze, mapOf())
        )

        println(response.hasError())
        println(response.getResponse())
    }
}