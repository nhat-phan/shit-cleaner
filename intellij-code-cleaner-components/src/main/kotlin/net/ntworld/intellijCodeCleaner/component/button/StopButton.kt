package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class StopButton(private val componentFactory: ComponentFactory) : AnAction(null, null, Icons.Stop) {
    private val plugin = componentFactory.makeDispatcher()

    override fun actionPerformed(e: AnActionEvent) {
        plugin dispatch RequestStopAnalyzeAction.make(
            componentFactory.makeInfrastructure(),
            plugin.store.project.id
        )
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = plugin.store.mainToolbar.analyzing
    }

}