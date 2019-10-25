package net.ntworld.intellijCodeCleaner.state

import org.joda.time.DateTime

data class ProjectState(
    val id: String,
    val initialized: Boolean,
    val analyzing: Boolean,
    val counting: Boolean,
    val parsing: Boolean,
    val hasResult: Boolean,
    val lastRunAt: DateTime?
) {
    companion object {
        val Default = ProjectState(
            id = "",
            initialized = false,
            analyzing = false,
            counting = false,
            parsing = false,
            hasResult = false,
            lastRunAt = null
        )
    }
}
