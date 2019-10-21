package net.ntworld.codeCleaner.requestHandler

import net.ntworld.codeCleaner.request.CheckLicenseRequest
import net.ntworld.codeCleaner.response.CheckLicenseResponse
import net.ntworld.codeCleaner.structure.License
import net.ntworld.codeClimate.make
import net.ntworld.foundation.Handler
import net.ntworld.foundation.RequestHandler

@Handler
class CheckLicenseRequestHandler : RequestHandler<CheckLicenseRequest, CheckLicenseResponse> {

    override fun handle(request: CheckLicenseRequest): CheckLicenseResponse {
        return CheckLicenseResponse.make(
            error = null,
            license = License.make(
                multiProjects = false,
                analyzeHistory = false
            )
        )
    }

}