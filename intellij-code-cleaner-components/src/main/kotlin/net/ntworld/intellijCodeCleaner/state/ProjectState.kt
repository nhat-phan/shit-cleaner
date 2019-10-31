package net.ntworld.intellijCodeCleaner.state

import net.ntworld.codeCleaner.statistic.CodeStatisticData
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import org.joda.time.DateTime

data class ProjectState(
    val id: String,
    val basePath: String,
    val contentRoots: List<ContentRootInfo>,
    val initialized: Boolean,
    val analyzing: Boolean,
    val counting: Boolean,
    val hasResult: Boolean,
    val codeSmells: Map<String, Issue>,
    val duplications: Map<String, Issue>,
    val codeStatisticData: CodeStatisticData?,
    val time: DateTime?
) {
    companion object {
        val Default = ProjectState(
            id = "",
            basePath = "",
            contentRoots = listOf(),
            initialized = false,
            analyzing = false,
            counting = false,
            hasResult = false,
            codeSmells = mapOf(),
            duplications = mapOf(),
            codeStatisticData = null,
            time = null
        )
    }
}
