package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE_SUCCESS
import net.ntworld.redux.Action

open class RequestAnalyzeSuccessAction : Action.EmptyPayload {
    override val type: String = REQUEST_ANALYZE_SUCCESS

    companion object {
        fun make(projectId: String): RequestAnalyzeSuccessAction {
            return RequestAnalyzeSuccessAction()
        }
    }
}

