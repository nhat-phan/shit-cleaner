package net.ntworld.codeClimate.structure

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("issue")
internal data class AnalyzedIssueImpl(
    override val categories: List<String>,

    @SerialName("check_name")
    override val checkName: String,

    override val content: ContentImpl,

    override val description: String,

    override val fingerprint: String,

    override val location: LocationImpl,

    @SerialName("other_locations")
    override val otherLocations: List<LocationImpl>,

    @SerialName("remediation_points")
    override val remediationPoints: Int,

    override val severity: String,

    @SerialName("engine_name")
    override val engineName: String
) : AnalyzedIssue