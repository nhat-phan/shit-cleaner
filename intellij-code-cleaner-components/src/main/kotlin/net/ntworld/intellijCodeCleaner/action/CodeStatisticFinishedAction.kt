package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.statistic.CodeStatisticData
import net.ntworld.intellijCodeCleaner.CODE_STATISTIC_FINISHED
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import net.ntworld.redux.Action

class CodeStatisticFinishedAction(
    projectId: String,
    data: CodeStatisticData,
    contentRoots: List<ContentRootInfo>
) : Action<CodeStatisticFinishedAction.Payload> {
    override val type: String = CODE_STATISTIC_FINISHED

    override val payload: Payload = Payload(
        mapOf(
            "projectId" to projectId,
            "data" to data,
            "contentRoots" to contentRoots
        )
    )

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map

        val data: CodeStatisticData by map

        val contentRoots: List<ContentRootInfo> by map
    }
}