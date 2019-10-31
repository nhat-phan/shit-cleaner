package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.PROJECT_INITIALIZED
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import net.ntworld.redux.Action

class ProjectInitializedAction(
    projectId: String,
    basePath: String,
    contentRoots: List<ContentRootInfo>
) : Action<ProjectInitializedAction.Payload> {
    override val type: String = PROJECT_INITIALIZED

    override val payload = Payload(
        mapOf(
            "projectId" to projectId,
            "basePath" to basePath,
            "contentRoots" to contentRoots
        )
    )

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map
        val basePath: String by map
        val contentRoots: List<ContentRootInfo> by map
    }
}