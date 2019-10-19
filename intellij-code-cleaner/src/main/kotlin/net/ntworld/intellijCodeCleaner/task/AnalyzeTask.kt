package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.project.Project
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.command.TerminateProcessCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner

class AnalyzeTask(
    currentProject: Project
) : Task.Backgroundable(currentProject, "Analyzing Code Smells and Duplications...", true) {
    private var watchId: String? = null
    private var completedCb: (() -> Unit)? = null
    val indicator: ProgressIndicator = Indicator(this)

    override fun run(indicator: ProgressIndicator) {
        val codeClimate = IntellijCodeCleaner()
        codeClimate {
            val id = codeClimate.idGeneratorOf().generate()

            watchId = id
            commandBus().process(AnalyzeCommand.make(cwd = project.basePath!!, watchId = id, timeout = -1))
        }
    }

    fun complete(cb: (() -> Unit)?) {
        this.completedCb = cb
    }

    private fun terminate() {
        val id = watchId
        if (null === id) {
            return
        }
        IntellijCodeCleaner().commandBus().process(
            TerminateProcessCommand.make(watchId = id)
        )
        watchId = null
    }

    private fun triggerCompleted() {
        val cb = completedCb
        if (null !== cb) {
            cb()
        }
    }

    private class Indicator(private val task: AnalyzeTask) : BackgroundableProcessIndicator(task) {

        override fun onRunningChange() {
            if (this.isCanceled) {
                task.terminate()
            }
            if (!this.isRunning) {
                task.triggerCompleted()
            }
        }

    }
}