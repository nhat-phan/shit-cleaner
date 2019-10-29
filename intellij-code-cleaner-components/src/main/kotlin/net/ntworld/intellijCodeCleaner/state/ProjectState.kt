package net.ntworld.intellijCodeCleaner.state

import net.ntworld.codeCleaner.statistic.CodeStatisticData
import net.ntworld.codeCleaner.structure.Issue
import org.joda.time.DateTime

data class ProjectState(
    val id: String,
    val initialized: Boolean,
    val analyzing: Boolean,
    val counting: Boolean,
    val hasResult: Boolean,
    val codeSmells: List<Issue>,
    val duplications: List<Issue>,
    val codeStatisticData: CodeStatisticData?,
    val time: DateTime?
) {
    companion object {
        val Default = ProjectState(
            id = "",
            initialized = false,
            analyzing = false,
            counting = false,
            hasResult = false,
            codeSmells = listOf(),
            duplications = listOf(),
            codeStatisticData = null,
            time = null
        )
    }
}
