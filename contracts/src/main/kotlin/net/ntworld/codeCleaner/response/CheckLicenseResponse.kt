package net.ntworld.codeCleaner.response

import net.ntworld.codeCleaner.error.NoLicenseError
import net.ntworld.codeCleaner.structure.License
import net.ntworld.foundation.Response

interface CheckLicenseResponse : Response {
    override val error: NoLicenseError?

    val license: License

    companion object
}
