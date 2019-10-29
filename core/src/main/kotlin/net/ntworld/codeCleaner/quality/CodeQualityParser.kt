package net.ntworld.codeCleaner.quality

import net.ntworld.codeCleaner.Serializer
import net.ntworld.codeCleaner.codeClimate.AnalyzedIssue
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.codeCleaner.structure.Severity
import net.ntworld.codeCleaner.structure.generated.IssueImpl
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

object CodeQualityParser {
    private val commonMarkParser = Parser.builder().build()
    private val htmlRenderer = HtmlRenderer.builder().build()

    fun parse(raw: String): Pair<List<Issue>, List<Issue>> {
        val data = Serializer.parseIssues(raw)
        val codeSmells = mutableListOf<Issue>()
        val duplications = mutableListOf<Issue>()

        val remediationByPaths = mutableMapOf<String, Int>()
        for (item in data) {
            if (null === remediationByPaths[item.location.path]) {
                remediationByPaths[item.location.path] = 0
            }
            remediationByPaths[item.location.path] = remediationByPaths[item.location.path]!! + item.remediationPoints
        }

        for (item in data) {
            val issue = IssueImpl(
                id = item.fingerprint,
                path = item.location.path,
                lines = item.location.lines,
                locations = item.otherLocations,
                description = item.description,
                content = markdownToHtml(item.content.body),
                point = item.remediationPoints,
                rate = rate(item.remediationPoints),
                fileRate = rateFile(remediationByPaths[item.location.path]!!),
                severity = if (item.severity == "major") Severity.MAJOR else Severity.MINOR
            )

            if (item.categories.contains("Duplication")) {
                duplications.add(issue)
            }
            if (item.categories.contains("Complexity")) {
                codeSmells.add(issue)
            }
        }
        return Pair(codeSmells, duplications)
    }

    internal fun rate(itemRemediationPoints: Int): MaintainabilityRate {
        if (itemRemediationPoints < 500000) {
            return MaintainabilityRate.Good
        }

        if (itemRemediationPoints < 2000000) {
            return MaintainabilityRate.Moderate
        }

        return MaintainabilityRate.Bad
    }

    internal fun rateFile(fileRemediationPoints: Int): MaintainabilityRate {
        // @see https://docs.codeclimate.com/docs/code-climate-glossary#section-remediation
        if (fileRemediationPoints < 4000000) {
            return MaintainabilityRate.Good
        }

        if (fileRemediationPoints < 16000000) {
            return MaintainabilityRate.Moderate
        }

        return MaintainabilityRate.Bad
    }

    private fun markdownToHtml(md: String): String {
        return htmlRenderer.render(commonMarkParser.parse(md))
    }
}