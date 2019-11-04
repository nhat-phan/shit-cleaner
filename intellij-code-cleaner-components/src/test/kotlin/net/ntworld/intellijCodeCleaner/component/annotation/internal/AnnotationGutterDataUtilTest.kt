package net.ntworld.intellijCodeCleaner.component.annotation.internal

import net.ntworld.codeCleaner.quality.CodeQualityParser
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.component.annotation.AnnotationGutterData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnnotationGutterDataUtilTest {

    @Test
    fun `test groupLinesAndIssues`() {
        val pair = buildSortedIssuesAndTypeOfIssuesMap(
            "foundation-generator/src/test/kotlin/net/ntworld/foundation/generator/util/ConstructorComposerTest.kt"
        )
        val result = AnnotationGutterDataUtil.groupLinesAndIssues("project", pair.first, pair.second)
        assertIssuesAreDistributedCorrectly(result.first, result.second)
        assertEquals(3, result.first.size)
    }

    private fun assertIssuesAreDistributedCorrectly(
        linesList: List<Map<Int, AnnotationGutterData.Line>>,
        issuesList: List<Map<String, AnnotationGutterData.Item>>
    ) {
        for (index in 0..linesList.lastIndex) {
            for (line in linesList[index]) {
                assertTrue(issuesList[index].containsKey(line.value.issueId))
            }
        }
    }

    private fun buildSortedIssuesAndTypeOfIssuesMap(
        path: String
    ): Pair<List<Issue>, Map<String, AnnotationGutterData.ItemType>> {
        val raw = AnnotationGutterDataUtilTest::class.java.getResource(
            "/analysis-results/foundation-v0.5.1.json"
        ).readText()

        val (rawCodeSmells, rawDuplications) = CodeQualityParser.parse(raw)
        val codeSmells = mutableMapOf<String, Issue>()
        rawCodeSmells.forEach { codeSmells[it.id] = it }

        val duplications = mutableMapOf<String, Issue>()
        rawDuplications.forEach { duplications[it.id] = it }

        val allIssues = mutableListOf<Issue>()
        val typeOfIssuesMap = mutableMapOf<String, AnnotationGutterData.ItemType>()
        AnnotationGutterDataUtil.loopAndAssigns(
            { it == path },
            allIssues,
            typeOfIssuesMap,
            codeSmells.values,
            AnnotationGutterData.ItemType.CODE_SMELL
        )
        AnnotationGutterDataUtil.loopAndAssigns(
            { it == path },
            allIssues,
            typeOfIssuesMap,
            duplications.values,
            AnnotationGutterData.ItemType.DUPLICATION
        )

        allIssues.sortByDescending { it.numberOfLines }
        return Pair(allIssues, typeOfIssuesMap)
    }

}
