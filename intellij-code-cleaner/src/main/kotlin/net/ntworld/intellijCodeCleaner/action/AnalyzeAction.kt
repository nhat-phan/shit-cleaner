package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.intellijCodeCleaner.util.Icons

object AnalyzeAction : AnAction(null, null, Icons.Analyze) {

    private var isRunning: Boolean = false

    fun isRunning(): Boolean {
        return isRunning
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            perform(project)
        }
    }

    fun perform(project: Project) {
        if (isRunning) {
            return
        }

        val task = AnalyzeTask(project)
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, task.indicator)
        isRunning = true
        task.complete {
            isRunning = false
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = null !== e.project && !isRunning
    }

}
