package net.ntworld.codeClimate.commandHandler

import net.ntworld.codeClimate.CoreInfrastructure
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.foundation.Handler
import net.ntworld.foundation.Use
import net.ntworld.foundation.cqrs.CommandHandler

@Handler
class AnalyzeCommandHandler(
    private val infrastructure: CoreInfrastructure
) : CommandHandler<AnalyzeCommand> {

    @Use(contract = ExecuteRequest::class)
    override fun handle(command: AnalyzeCommand) = infrastructure {
        // commandBus().process()

//        val runtime = Runtime.getRuntime()
//        val process = runtime.exec("codeclimate --help")
//        process.exitValue()
        // val out = serviceBus().process(Ex)

        // commandBus().process()
    }

}