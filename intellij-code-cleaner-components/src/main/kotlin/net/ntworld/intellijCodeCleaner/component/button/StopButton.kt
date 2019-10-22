package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.StopAnalyzeAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class StopButton(private val plugin: Plugin) : AnAction(null, null, Icons.Stop) {

    override fun actionPerformed(e: AnActionEvent) {
        plugin dispatch StopAnalyzeAction.make(plugin.store.project.id)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = plugin.store.mainToolbar.analyzing
    }

}