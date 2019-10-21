package net.ntworld.codeCleaner.error

import net.ntworld.foundation.Error

interface NotSupportedFeature : Error {
    override val type: String
        get() = "net.ntworld.codeCleaner.error.NoLicenseError"

    override val code: Int
        get() = 403
}