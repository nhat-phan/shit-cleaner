package net.ntworld.codeCleaner.query

import net.ntworld.codeCleaner.structure.Project
import net.ntworld.foundation.cqrs.Query

interface FindProjectByIdQuery : Query<Project> {
    val id: String

    companion object
}