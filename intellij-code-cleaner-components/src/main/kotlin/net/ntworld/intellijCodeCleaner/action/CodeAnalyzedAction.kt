package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.CODE_ANALYZED
import net.ntworld.redux.Action

class CodeAnalyzedAction(projectId: String) : Action<CodeAnalyzedAction.Payload> {
    override val type: String = CODE_ANALYZED

    override val payload: Payload = Payload(
        mapOf(
            "projectId" to projectId
        )
    )

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map
    }
}