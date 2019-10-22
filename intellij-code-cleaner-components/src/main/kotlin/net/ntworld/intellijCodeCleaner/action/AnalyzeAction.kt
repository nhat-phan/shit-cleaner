package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.project.Project
import net.ntworld.intellijCodeCleaner.ANALYZE_PROJECT
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.redux.Action

open class AnalyzeAction : Action.EmptyPayload {
    override val type: String = ANALYZE_PROJECT

    companion object {
        fun make(plugin: Plugin, projectId: String, project: Project): AnalyzeAction {
            AnalyzeTask.start(plugin, projectId, project)

            return AnalyzeAction()
        }
    }
}

