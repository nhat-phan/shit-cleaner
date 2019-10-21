package net.ntworld.codeCleaner.query

import net.ntworld.foundation.cqrs.Query

interface GetAllProjectsQuery : Query<GetAllProjectsQueryResult> {
    val cwd: String

    companion object
}
