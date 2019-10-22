package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.project.Project
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner
import net.ntworld.intellijCodeCleaner.IntellijCodeCleanerPlugin
import net.ntworld.intellijCodeCleaner.action.StopAnalyzeAction

class AnalyzeTask(
    currentProject: Project
) : Task.Backgroundable(currentProject, "Analyzing Code Smells and Duplications...", true) {
    val indicator: ProgressIndicator = Indicator(this)

    override fun run(indicator: ProgressIndicator) {
        val infrastructure = IntellijCodeCleaner()
        infrastructure {
            IntellijCodeCleanerPlugin.selectedProjectIds.forEach {
                commandBus().process(CreateAnalyzeProcessCommand.make(it))
            }
        }
    }

    private fun terminate() {
        StopAnalyzeAction.perform()
    }

    private class Indicator(private val task: AnalyzeTask) : BackgroundableProcessIndicator(task) {

        override fun onRunningChange() {
            if (this.isCanceled) {
                task.terminate()
            }
        }

    }
}