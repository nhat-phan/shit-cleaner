package net.ntworld.env.requestHandler

import net.ntworld.codeClimate.make
import net.ntworld.env.Env
import net.ntworld.env.error.ExecuteError
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.env.response.ExecuteResponse
import net.ntworld.foundation.Handler
import net.ntworld.foundation.RequestHandler
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.ExecuteWatchdog
import org.apache.commons.exec.PumpStreamHandler
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

@Handler
class ExecuteRequestHandler : RequestHandler<ExecuteRequest, ExecuteResponse> {

    override fun handle(request: ExecuteRequest): ExecuteResponse {
        val stdout = ByteArrayOutputStream()
        val stderr = ByteArrayOutputStream()
        val pumpStreamHandler = PumpStreamHandler(stdout, stderr)

        val executor = DefaultExecutor()
        executor.workingDirectory = Paths.get(request.workingDirectory).toFile()
        executor.streamHandler = pumpStreamHandler

        if (request.timeout > 0) {
            val watchdog = ExecuteWatchdog((request.timeout * 1000).toLong())
            executor.watchdog = watchdog
        }

        try {
            val code = executor.execute(makeCommandLine(request))

            if (0 != code) {
                return this.error(stderr.toString(), code)
            }
            return this.success(stdout.toString())
        } catch (e: Exception) {
            return this.error(e.message.toString(), -1)
        }
    }

    private fun makeCommandLine(request: ExecuteRequest): CommandLine {
        val commandLine = CommandLine(if (Env.isWindows()) "cmd" else "bash")
        commandLine.addArgument(if (Env.isWindows()) "/C" else "-c")

        commandLine.addArgument(request.command, false)
        if (request.arguments.isNotEmpty()) {
            request.arguments.forEach { (key, value) ->
                commandLine.addArgument(key)
                commandLine.addArgument(value)
            }
        }
        return commandLine
    }

    private fun error(message: String, code: Int): ExecuteResponse {
        return ExecuteResponse.make(
            error = ExecuteError.make(message = message, code = code),
            output = ""
        )
    }

    private fun success(output: String): ExecuteResponse {
        return ExecuteResponse.make(
            error = null,
            output = output
        )
    }

}