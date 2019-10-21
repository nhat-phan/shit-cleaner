package net.ntworld.codeCleaner.error

import net.ntworld.foundation.Error

interface NoLicenseError : Error {
    override val type: String
        get() = "net.ntworld.codeCleaner.error.NoLicenseError"

    override val code: Int
        get() = 403

    companion object
}