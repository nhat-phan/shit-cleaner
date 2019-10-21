package net.ntworld.codeCleaner.structure

import net.ntworld.foundation.cqrs.QueryResult

interface Project: QueryResult {
    val id: String

    val name: String

    val path: String

    companion object
}
