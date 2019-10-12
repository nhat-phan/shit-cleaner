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
        println(System.getProperty("os.name"))

        val response = infrastructure.serviceBus().process(
            ExecuteRequest.make("ls -al /", mapOf())
        ) {
            println("Error: ${it.error}" )
        }

        println(response)
    }

}