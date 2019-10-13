package net.ntworld.codeClimate.commandHandler

import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.foundation.Handler
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class AnalyzeCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<AnalyzeCommand> {

    override fun handle(command: AnalyzeCommand) {
        val response = infrastructure.serviceBus().process(
            ExecuteRequest.make(command.basePath, CLI_COMMAND, mapOf())
        ) {
            println("Error: ${it.error}")
        }

        println(response)
    }

    companion object {
        // const val CLI_COMMAND = "\"codeclimate analyze -f json > tmp.json\""
        // const val CLI_COMMAND = "ls -al /usr/local/bin"
        // const val CLI_COMMAND = "env"
        // const val CLI_COMMAND = "codeclimate analyze"
        const val CLI_COMMAND = "codeclimate analyze -f json > ./tmp.json"
    }
}