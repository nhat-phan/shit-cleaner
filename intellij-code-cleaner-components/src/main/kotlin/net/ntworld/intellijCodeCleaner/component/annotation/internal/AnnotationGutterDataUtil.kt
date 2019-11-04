package net.ntworld.intellijCodeCleaner.component.annotation.internal

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterData

object AnnotationGutterDataUtil {

    fun groupLinesAndIssues(
        projectId: String,
        sortedIssues: List<Issue>,
        typeOfIssuesMap: Map<String, AnnotationGutterData.ItemType>
    ): Pair<List<Map<Int, AnnotationGutterData.Line>>, List<Map<String, AnnotationGutterData.Item>>> {
        val linesList = mutableListOf<MutableMap<Int, AnnotationGutterData.Line>>(mutableMapOf())
        val issuesList = mutableListOf<MutableMap<String, AnnotationGutterData.Item>>(mutableMapOf())
        for (issue in sortedIssues) {
            val type = typeOfIssuesMap[issue.id]!!
            collectIssueLines(issue, projectId, type, linesList, issuesList)

            collectDuplicatedLocationIssuesInTheSameFile(issue, linesList)

            issuesList[issuesList.lastIndex][issue.id] = AnnotationGutterData.Item(
                id = issue.id,
                projectId = projectId,
                type = type,
                description = issue.description,
                rate = issue.rate
            )
        }
        return Pair(linesList, issuesList)
    }

    private fun collectIssueLines(
        issue: Issue,
        projectId: String,
        type: AnnotationGutterData.ItemType,
        lines: MutableList<MutableMap<Int, AnnotationGutterData.Line>>,
        issues: MutableList<MutableMap<String, AnnotationGutterData.Item>>
    ) {
        for (i in issue.lines.begin..issue.lines.end) {
            if (lines[lines.lastIndex].containsKey(i) &&
                lines[lines.lastIndex][i]!!.issueId != issue.id
            ) {
                lines.add(mutableMapOf())
                issues.add(mutableMapOf())
            }

            lines[lines.lastIndex][i] = AnnotationGutterData.Line(
                start = i == issue.lines.begin, issueId = issue.id,
                original = true
            )
            issues[issues.lastIndex][issue.id] = AnnotationGutterData.Item(
                id = issue.id, projectId = projectId, type = type,
                description = issue.description, rate = issue.rate
            )
        }
    }

    private fun collectDuplicatedLocationIssuesInTheSameFile(
        issue: Issue,
        lines: MutableList<MutableMap<Int, AnnotationGutterData.Line>>
    ) {
        issue.locations.forEach {
            if (it.path != issue.path) {
                return@forEach
            }

            for (i in it.lines.begin..it.lines.end) {
                lines[lines.lastIndex][i] = AnnotationGutterData.Line(
                    start = i == it.lines.begin, issueId = issue.id,
                    original = false
                )
            }
        }
    }

    fun loopAndAssigns(
        issueFilter: (String) -> Boolean,
        allIssues: MutableList<Issue>,
        typeOfIssuesMap: MutableMap<String, AnnotationGutterData.ItemType>,
        issues: Collection<Issue>,
        type: AnnotationGutterData.ItemType
    ) {
        issues
            .filter { issueFilter(it.path) }
            .forEach {
                allIssues.add(it)
                typeOfIssuesMap[it.id] = type
            }
    }

    fun calcBackgroundOpacity(index: Int, total: Int): Int {
        val step = if (total < 5) 0.1f else 0.5f / total
        val percent = 1.0f - (index * step)
        return (255 * percent).toInt()
    }

}