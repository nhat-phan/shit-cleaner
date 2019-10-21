package net.ntworld.codeCleaner.query

import net.ntworld.codeCleaner.structure.ProjectState
import net.ntworld.foundation.cqrs.Query

interface FindProjectStateByIdQuery: Query<ProjectState> {
    val id: String

    companion object
}
