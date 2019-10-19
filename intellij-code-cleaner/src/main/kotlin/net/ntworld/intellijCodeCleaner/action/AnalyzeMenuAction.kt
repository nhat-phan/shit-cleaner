package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindowManager

class AnalyzeMenuAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Code Cleaner")
            toolWindow.show {
                toolWindow.contentManager.setSelectedContent(
                    toolWindow.contentManager.getContent(0)!!,
                    true
                )
                AnalyzeAction.perform(project)
            }
        }
    }

}