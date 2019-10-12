package net.ntworld.env.response

import net.ntworld.env.error.ExecuteError
import net.ntworld.foundation.Response

interface ExecuteResponse : Response {
    override val error: ExecuteError?

    companion object
}