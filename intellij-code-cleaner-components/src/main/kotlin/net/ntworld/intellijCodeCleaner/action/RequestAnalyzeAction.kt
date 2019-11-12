package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.ComponentFactory
import com.intellij.openapi.project.Project as IdeaProject
import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.intellijCodeCleaner.task.CountLineOfCodeTask
import net.ntworld.redux.Action

open class RequestAnalyzeAction : Action.EmptyPayload {
    override val type: String = REQUEST_ANALYZE

    companion object {
        fun make(
            componentFactory: ComponentFactory,
            projectId: String,
            ideaProject: IdeaProject
        ): RequestAnalyzeAction {
            AnalyzeTask.start(componentFactory, projectId, ideaProject)
            CountLineOfCodeTask.start(componentFactory.makeDispatcher(ideaProject), projectId, ideaProject)

            return RequestAnalyzeAction()
        }
    }
}

