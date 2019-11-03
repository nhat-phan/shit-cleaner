package net.ntworld.intellijCodeCleaner.component.annotation.internal

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterData

object AnnotationGutterDataUtil {

    fun groupLinesAndIssues(
        projectId: String,
        sortedIssues: List<Issue>,
        typeOfIssuesMap: Map<String, AnnotationGutterData.ItemType>
    ): Pair<List<Map<Int, AnnotationGutterData.Line>>, List<Map<String, AnnotationGutterData.Item>>> {
        val lines = mutableListOf<MutableMap<Int, AnnotationGutterData.Line>>(mutableMapOf())
        val issues = mutableListOf<MutableMap<String, AnnotationGutterData.Item>>(mutableMapOf())
        for (issue in sortedIssues) {
            for (i in issue.lines.begin..issue.lines.end) {
                if (lines[lines.lastIndex].containsKey(i)) {
                    lines.add(mutableMapOf())
                    issues.add(mutableMapOf())
                }

                lines[lines.lastIndex][i] =
                    AnnotationGutterData.Line(
                        start = i == issue.lines.begin, issueId = issue.id,
                        original = true
                    )
            }

            issue.locations.filter { it.path == issue.path }
                .forEach {
                    for (i in it.lines.begin..it.lines.end) {
                        lines[lines.lastIndex][i] =
                            AnnotationGutterData.Line(
                                start = i == it.lines.begin, issueId = issue.id,
                                original = false
                            )
                    }
                }

            issues[issues.lastIndex][issue.id] =
                AnnotationGutterData.Item(
                    id = issue.id,
                    projectId = projectId,
                    type = typeOfIssuesMap[issue.id]!!,
                    description = issue.description,
                    rate = issue.rate
                )
        }
        return Pair(lines, issues)
    }

}