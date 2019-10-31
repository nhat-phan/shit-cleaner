package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindCodeQualityByIdQuery
import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.CODE_ANALYZED
import net.ntworld.intellijCodeCleaner.CodeCleaner
import net.ntworld.redux.Action
import org.joda.time.DateTime

class CodeAnalyzedAction private constructor(
    projectId: String,
    codeQualityId: String,
    codeSmells: List<Issue>,
    duplications: List<Issue>,
    analyzeProcessStartAt: DateTime,
    analyzeProcessEndAt: DateTime,
    createdAt: DateTime
) : Action<CodeAnalyzedAction.Payload> {
    override val type: String = CODE_ANALYZED

    override val payload: Payload = Payload(
        mapOf(
            "projectId" to projectId,
            "codeQualityId" to codeQualityId,
            "codeSmells" to codeSmells,
            "duplications" to duplications,
            "analyzeProcessStartAt" to analyzeProcessStartAt,
            "analyzeProcessEndAt" to analyzeProcessEndAt,
            "createdAt" to createdAt
        )
    )

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map
        val codeQualityId: String by map
        val codeSmells: List<Issue> by map
        val duplications: List<Issue> by map
        val analyzeProcessStartAt: DateTime by map
        val analyzeProcessEndAt: DateTime by map
        val createdAt: DateTime by map
    }

    companion object {
        fun make(projectId: String, codeQualityId: String): CodeAnalyzedAction {
            val result = CodeCleaner().queryBus().process(FindCodeQualityByIdQuery.make(codeQualityId))
            return CodeAnalyzedAction(
                projectId = result.projectId,
                codeQualityId = result.id,
                codeSmells = result.codeSmells,
                duplications = result.duplications,
                analyzeProcessStartAt = DateTime(result.analyzeProcessStartAt),
                analyzeProcessEndAt = DateTime(result.analyzeProcessEndAt),
                createdAt = DateTime(result.createdAt)
            )
        }
    }
}