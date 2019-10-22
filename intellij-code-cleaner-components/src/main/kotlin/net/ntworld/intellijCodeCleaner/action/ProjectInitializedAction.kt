package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.PROJECT_INITIALIZED
import net.ntworld.redux.Action

class ProjectInitializedAction(override val payload: Payload?) : Action<ProjectInitializedAction.Payload> {
    override val type: String = PROJECT_INITIALIZED

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map
    }

    companion object {
        fun make(projectId: String): ProjectInitializedAction {
            return ProjectInitializedAction(
                Payload(
                    mapOf(
                        "projectId" to projectId
                    )
                )
            )
        }
    }
}