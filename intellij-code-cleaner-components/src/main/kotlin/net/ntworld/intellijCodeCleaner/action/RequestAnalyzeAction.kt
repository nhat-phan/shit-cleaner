package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.project.Project as IdeaProject
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.intellijCodeCleaner.task.CountLineOfCodeTask
import net.ntworld.redux.Action

open class RequestAnalyzeAction : Action.EmptyPayload {
    override val type: String = REQUEST_ANALYZE

    companion object {
        fun make(plugin: Plugin, projectId: String, ideaProject: IdeaProject): RequestAnalyzeAction {
            AnalyzeTask.start(plugin, projectId, ideaProject)
            CountLineOfCodeTask.start(plugin, projectId, ideaProject)

            return RequestAnalyzeAction()
        }
    }
}

