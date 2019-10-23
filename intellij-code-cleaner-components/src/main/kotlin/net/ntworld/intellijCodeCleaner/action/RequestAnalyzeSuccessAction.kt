package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE_SUCCESS

class RequestAnalyzeSuccessAction(projectId: String) : AbstractProjectIdAction(projectId) {
    override val type: String = REQUEST_ANALYZE_SUCCESS
}

