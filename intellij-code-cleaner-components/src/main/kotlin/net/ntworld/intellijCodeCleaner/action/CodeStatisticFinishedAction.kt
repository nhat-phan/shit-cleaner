package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.statistic.CodeStatisticData
import net.ntworld.intellijCodeCleaner.CODE_STATISTIC_FINISHED
import net.ntworld.redux.Action

class CodeStatisticFinishedAction(
    projectId: String, data: CodeStatisticData
) : Action<CodeStatisticFinishedAction.Payload> {
    override val type: String = CODE_STATISTIC_FINISHED

    override val payload: Payload = Payload(
        mapOf(
            "projectId" to projectId,
            "data" to data
        )
    )

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map

        val data: CodeStatisticData by map
    }
}