package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.project.Project as IdeaProject
import net.ntworld.codeCleaner.command.CreateAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeAction

class AnalyzeTask private constructor(
    private val componentFactory: ComponentFactory,
    private val projectId: String,
    private val ideaProject: IdeaProject
) : Task.Backgroundable(ideaProject, "Analyzing Code Smells and Duplications...", true) {
    private val indicator: ProgressIndicator = Indicator(this)

    override fun run(indicator: ProgressIndicator) {
        val infrastructure = componentFactory.makeInfrastructure()
        infrastructure {
            commandBus().process(CreateAnalyzeProcessCommand.make(projectId))
        }
    }

    private fun terminate() {
        componentFactory.makeDispatcher(ideaProject) dispatch RequestStopAnalyzeAction.make(
            componentFactory.makeInfrastructure(),
            projectId
        )
    }

    private class Indicator(private val task: AnalyzeTask) : BackgroundableProcessIndicator(task) {

        override fun onRunningChange() {
            if (this.isCanceled) {
                task.terminate()
            }
        }

    }

    companion object {
        fun start(componentFactory: ComponentFactory, projectId: String, ideaProject: IdeaProject) {
            componentFactory.makeAnnotationManager(ideaProject).hide()
            val task = AnalyzeTask(componentFactory, projectId, ideaProject)
            ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, task.indicator)
        }
    }
}