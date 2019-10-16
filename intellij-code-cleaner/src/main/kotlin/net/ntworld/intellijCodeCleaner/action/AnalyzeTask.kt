package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner

class AnalyzeTask(
    currentProject: Project
) : Task.Backgroundable(currentProject, "Analyzing Code Smells and Duplications...", false) {
    override fun run(indicator: ProgressIndicator) {
        val codeClimate = IntellijCodeCleaner()
        codeClimate {
            val analyzeCommand = AnalyzeCommand.make(
                basePath = project.basePath!!,
                path = null
            )
            commandBus().process(analyzeCommand)
        }
    }
}