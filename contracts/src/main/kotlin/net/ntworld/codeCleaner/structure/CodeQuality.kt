package net.ntworld.codeCleaner.structure

import net.ntworld.foundation.cqrs.QueryResult

interface CodeQuality: QueryResult {
    val id: String

    val projectId: String

    val codeSmells: List<Issue>

    val duplications: List<Issue>

    val analyzeProcessStartAt: String

    val analyzeProcessEndAt: String

    val createdAt: String
}
