package net.ntworld.intellijCodeCleaner.component

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindowManager
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.TOOL_WINDOW_NAME
import net.ntworld.intellijCodeCleaner.action.RequestAnalyzeAction

abstract class AnalyzeMenuActionBase : AnAction() {

    abstract val componentFactory: ComponentFactory

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_NAME)
            toolWindow.show {
                toolWindow.contentManager.setSelectedContent(
                    toolWindow.contentManager.getContent(0)!!,
                    true
                )
                val dispatcher = componentFactory.makeDispatcher()
                dispatcher dispatch RequestAnalyzeAction.make(
                    dispatcher,
                    dispatcher.store.project.id,
                    project
                )
            }
        }
    }

}