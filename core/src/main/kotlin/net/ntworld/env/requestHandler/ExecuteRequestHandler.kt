package net.ntworld.env.requestHandler

import net.ntworld.codeClimate.make
import net.ntworld.env.Env
import net.ntworld.env.error.ExecuteError
import net.ntworld.env.request.ExecuteRequest
import net.ntworld.env.response.ExecuteResponse
import net.ntworld.foundation.Handler
import net.ntworld.foundation.RequestHandler
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Paths

@Handler
class ExecuteRequestHandler : RequestHandler<ExecuteRequest, ExecuteResponse> {

    override fun handle(request: ExecuteRequest): ExecuteResponse {
        val process = makeProcess(request)

        val code = process.waitFor()

        return if (0 != code) {
            ExecuteResponse.make(
                error = ExecuteError.make(
                    message = readStream(process.errorStream),
                    code = code
                ),
                output = readStream(process.inputStream)
            )
        } else {
            ExecuteResponse.make(
                error = null,
                output = readStream(process.inputStream)
            )
        }
    }

    private fun makeProcess(request: ExecuteRequest): Process {
        val builder = ProcessBuilder()
        builder.directory(Paths.get(request.workingDirectory).toFile())

        val command = StringBuffer()

        command.append(request.command)
        if (request.arguments.isNotEmpty()) {
            request.arguments.forEach { (key, value) ->
                command.append(' ')
                command.append(key)
                command.append(' ')
                command.append(value)
            }
        }

        return if (Env.isWindows()) {
            builder.command("cmd", "/c", command.toString()).start()
        } else {
            builder.command("bash", "-c", "$command").start()
        }
    }

    private fun readStream(stream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(stream))

        return reader.readText()
    }

}