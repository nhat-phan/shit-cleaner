package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import net.ntworld.intellijCodeCleaner.task.AnalyzeTask
import net.ntworld.intellijCodeCleaner.util.Icons

object AnalyzeAction : AnAction(null, null, Icons.Analyze) {

    private var running: Boolean = false
    private var runningTask: AnalyzeTask? = null

    fun isRunning(): Boolean {
        return running
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (null !== project) {
            perform(project)
        }
    }

    fun perform(project: Project) {
        if (running) {
            return
        }

        val task = AnalyzeTask(project)
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, task.indicator)
        running = true
        runningTask = task
        task.complete {
            running = false
            runningTask = null
        }
    }

    fun stop() {
        val task = runningTask
        if (null !== task) {
            task.indicator.cancel()
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = null !== e.project && !running
    }

}
