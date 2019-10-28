package net.ntworld.codeCleaner.structure

import net.ntworld.foundation.cqrs.QueryResult

interface CodeQuality: QueryResult {
    val id: String

    val codeSmells: List<Issue>

    val duplications: List<Issue>

    val createdAt: String
}
