package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner
import net.ntworld.intellijCodeCleaner.IntellijCodeCleanerPlugin
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.intellijCodeCleaner.util.Icons

object AnalyzeAction : AnAction(null, null, Icons.Analyze) {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            perform(project)
        }
    }

    fun perform(project: Project) {
        val task = AnalyzeTask(project)
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, task.indicator)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = !IntellijCodeCleanerPlugin.hasAnyAnalyzeProcessRunning()
    }

}
