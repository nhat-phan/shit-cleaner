package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.REQUEST_STOP_ANALYZE_SUCCESS

class RequestStopAnalyzeSuccessAction(projectId: String) : AbstractProjectIdAction(projectId) {
    override val type: String = REQUEST_STOP_ANALYZE_SUCCESS
}