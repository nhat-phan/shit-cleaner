package net.ntworld.intellijCodeCleaner.action

import net.ntworld.redux.Action

abstract class AbstractProjectIdAction(
    projectId: String
) : Action<AbstractProjectIdAction.Payload> {
    override val payload: Payload = Payload(mapOf("projectId" to projectId))

    class Payload(private val map: Map<String, Any>) : Map<String, Any> by map {
        val projectId: String by map
    }
}