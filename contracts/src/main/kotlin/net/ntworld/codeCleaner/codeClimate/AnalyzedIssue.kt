package net.ntworld.codeCleaner.codeClimate

interface AnalyzedIssue: AnalyzedResult {
    val categories: List<String>

    val checkName: String

    val content: Content

    val description: String

    val fingerprint: String

    val location: Location

    val otherLocations: List<Location>

    val remediationPoints: Int

    val severity: String

    val engineName: String
}
