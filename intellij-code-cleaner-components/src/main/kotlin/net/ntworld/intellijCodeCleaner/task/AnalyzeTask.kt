package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.project.Project
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.intellijCodeCleaner.CodeCleaner
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeAction

class AnalyzeTask private constructor(
    private val plugin: Plugin,
    private val projectId: String,
    ideaProject: Project
) : Task.Backgroundable(ideaProject, "Analyzing Code Smells and Duplications...", true) {
    private val indicator: ProgressIndicator = Indicator(this)

    override fun run(indicator: ProgressIndicator) {
        val infrastructure = CodeCleaner()
        infrastructure {
            commandBus().process(CreateAnalyzeProcessCommand.make(projectId))
        }
    }

    private fun terminate() {
        plugin.dispatch(RequestStopAnalyzeAction.make(projectId))
    }

    private class Indicator(private val task: AnalyzeTask) : BackgroundableProcessIndicator(task) {

        override fun onRunningChange() {
            if (this.isCanceled) {
                task.terminate()
            }
        }

    }

    companion object {
        fun start(plugin: Plugin, projectId: String, ideaProject: Project) {
            val task = AnalyzeTask(plugin, projectId, ideaProject)
            ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, task.indicator)
        }
    }
}