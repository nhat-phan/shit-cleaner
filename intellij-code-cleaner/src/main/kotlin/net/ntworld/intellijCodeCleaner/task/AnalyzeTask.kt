package net.ntworld.intellijCodeCleaner.task

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import net.ntworld.codeClimate.command.AnalyzeCommand
import net.ntworld.codeClimate.make
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner

class AnalyzeTask(
    currentProject: Project
) : Task.Backgroundable(currentProject, "Analyzing Code Smells and Duplications...", true) {
    private var watchId: String? = null

    override fun run(indicator: ProgressIndicator) {
        val codeClimate = IntellijCodeCleaner()
        codeClimate {
            val id = codeClimate.idGeneratorOf().generate()

            watchId = id
            commandBus().process(AnalyzeCommand.make(cwd = project.basePath!!, watchId = id, timeout = -1))
        }
    }

//    override fun onCancel() {
//        if (null !== executeWatchdogId) {
//            println("Kill execute process watched by watchdog $executeWatchdogId")
//        }
//        super.onCancel()
//    }
}