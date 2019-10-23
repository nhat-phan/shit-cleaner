package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.RequestAnalyzeAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class AnalyzeButton(private val plugin: Plugin) : AnAction(null, null, Icons.Analyze) {

    override fun actionPerformed(e: AnActionEvent) {
        plugin dispatch RequestAnalyzeAction.make(plugin, plugin.store.project.id, e.project!!)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = !plugin.store.mainToolbar.analyzing
    }

}
