package net.ntworld.intellijCodeClimate.action

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeClimate.IntellijCodeClimate

class AnalyzeTask(
    currentProject: Project
) : Task.Backgroundable(currentProject, "Analyzing Code Smells and Duplications...", false) {
    override fun run(indicator: ProgressIndicator) {
        val codeClimate = IntellijCodeClimate()
        codeClimate {
            val analyzeCommand = AnalyzeCommand.make(
                basePath = project.basePath!!,
                path = null
            )
            commandBus().process(analyzeCommand)
        }
    }
}