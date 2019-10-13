package net.ntworld.env.request

import net.ntworld.foundation.Request
import net.ntworld.env.response.ExecuteResponse

interface ExecuteRequest: Request<ExecuteResponse> {
    val workingDirectory: String

    val command: String

    val arguments: Map<String, String>

    companion object
}
