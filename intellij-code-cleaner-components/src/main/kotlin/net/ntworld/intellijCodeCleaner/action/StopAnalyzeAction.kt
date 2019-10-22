package net.ntworld.intellijCodeCleaner.action

import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.intellijCodeCleaner.CodeCleaner
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.STOP_ANALYZE_PROJECT
import net.ntworld.redux.Action

open class StopAnalyzeAction : Action.EmptyPayload {
    override val type: String = STOP_ANALYZE_PROJECT

    companion object {
        fun make(projectId: String): StopAnalyzeAction {
            val infrastructure = CodeCleaner()
            infrastructure {
                commandBus().process(DeleteAnalyzeProcessCommand.make(projectId))
            }

            return StopAnalyzeAction()
        }
    }
}