package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.TOGGLE_ANNOTATION
import net.ntworld.redux.Action

open class ToggleAnnotationAction : Action.EmptyPayload {
    override val type: String = TOGGLE_ANNOTATION

    companion object {
        fun make(): ToggleAnnotationAction {
            return ToggleAnnotationAction()
        }
    }
}