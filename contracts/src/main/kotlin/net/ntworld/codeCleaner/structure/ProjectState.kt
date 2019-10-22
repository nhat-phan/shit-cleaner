package net.ntworld.codeCleaner.structure

import net.ntworld.foundation.cqrs.QueryResult

interface ProjectState: QueryResult {
    val projectId: String

    val isRunning: Boolean

    companion object
}