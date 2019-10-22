package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.codeCleaner.command.DeleteAnalyzeProcessCommand
import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.intellijCodeCleaner.IntellijCodeCleaner
import net.ntworld.intellijCodeCleaner.IntellijCodeCleanerPlugin
import net.ntworld.intellijCodeCleaner.util.Icons

object StopAnalyzeAction : AnAction(null, null, Icons.Stop) {

    override fun actionPerformed(e: AnActionEvent) = perform()

    fun perform() {
        val infrastructure = IntellijCodeCleaner()
        IntellijCodeCleanerPlugin.selectedProjectIds.forEach {
            infrastructure.commandBus().process(DeleteAnalyzeProcessCommand.make(it))
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = IntellijCodeCleanerPlugin.hasAnyAnalyzeProcessRunning()
    }

}