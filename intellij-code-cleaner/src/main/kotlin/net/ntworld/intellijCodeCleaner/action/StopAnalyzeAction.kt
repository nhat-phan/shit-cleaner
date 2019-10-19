package net.ntworld.intellijCodeCleaner.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.util.Icons

object StopAnalyzeAction : AnAction(null, null, Icons.Stop) {

    override fun actionPerformed(e: AnActionEvent) {
        AnalyzeAction.stop()
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = AnalyzeAction.isRunning()
    }

}