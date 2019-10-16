package net.ntworld.intellijCodeClimate.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressManager

class AnalyzeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            ProgressManager.getInstance().run(AnalyzeTask(project))
        }
    }

}