package net.ntworld.intellijCodeCleaner.state

import net.ntworld.codeCleaner.statistic.CodeStatisticData
import org.joda.time.DateTime

data class ProjectState(
    val id: String,
    val initialized: Boolean,
    val analyzing: Boolean,
    val counting: Boolean,
    val hasResult: Boolean,
    val codeStatisticData: CodeStatisticData?,
    val lastRunAt: DateTime?
) {
    companion object {
        val Default = ProjectState(
            id = "",
            initialized = false,
            analyzing = false,
            counting = false,
            hasResult = false,
            codeStatisticData = null,
            lastRunAt = null
        )
    }
}
