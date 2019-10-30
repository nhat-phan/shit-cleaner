package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.TOGGLE_MAINTAINABILITY_FILTER
import net.ntworld.redux.Action

open class ToggleMaintainabilityFilterAction(rate: MaintainabilityRate) : Action<ToggleMaintainabilityFilterAction.Payload> {
    override val type: String = TOGGLE_MAINTAINABILITY_FILTER

    override val payload: Payload = Payload(
        mapOf(
            "rate" to rate
        )
    )

    class Payload(private val map: Map<String, Any>): Map<String, Any> by map {
        val rate: MaintainabilityRate by map
    }
}