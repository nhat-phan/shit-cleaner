package net.ntworld.codeCleaner.query

import net.ntworld.codeCleaner.structure.Project
import net.ntworld.foundation.cqrs.QueryResult

interface GetAllProjectsQueryResult : QueryResult {
    val projects: List<Project>

    companion object
}
