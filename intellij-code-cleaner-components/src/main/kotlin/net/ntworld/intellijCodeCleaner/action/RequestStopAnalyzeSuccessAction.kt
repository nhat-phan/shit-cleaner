package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.REQUEST_STOP_ANALYZE_SUCCESS
import net.ntworld.redux.Action

open class RequestStopAnalyzeSuccessAction : Action.EmptyPayload {
    override val type: String = REQUEST_STOP_ANALYZE_SUCCESS

    companion object {
        fun make(projectId: String): RequestStopAnalyzeSuccessAction {
            return RequestStopAnalyzeSuccessAction()
        }
    }
}