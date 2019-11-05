package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.foundation.Infrastructure
import net.ntworld.intellijCodeCleaner.REQUEST_STOP_ANALYZE
import net.ntworld.redux.Action

open class RequestStopAnalyzeAction : Action.EmptyPayload {
    override val type: String = REQUEST_STOP_ANALYZE

    companion object {
        fun make(infrastructure: Infrastructure, projectId: String): RequestStopAnalyzeAction {
            infrastructure {
                commandBus().process(DeleteAnalyzeProcessCommand.make(projectId))
            }

            return RequestStopAnalyzeAction()
        }
    }
}