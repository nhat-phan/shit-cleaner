package net.ntworld.env.error

import net.ntworld.foundation.Error

interface ExecuteError : Error {
    override val type: String
        get() = "net.ntworld.env.error.ExecuteError"

    companion object
}
