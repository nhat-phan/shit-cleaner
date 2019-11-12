package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.TOOLTIP_STOP_ANALYZE_BUTTON
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class StopButton(private val componentFactory: ComponentFactory) :
    AnAction(null, TOOLTIP_STOP_ANALYZE_BUTTON, Icons.Stop) {

    override fun actionPerformed(e: AnActionEvent) {
        componentFactory.useDispatcherOf(e.project) {
            this dispatch RequestStopAnalyzeAction.make(
                componentFactory.makeInfrastructure(),
                this.store.project.id
            )
        }
    }

    override fun update(e: AnActionEvent) {
        componentFactory.useDispatcherOf(e.project) {
            e.presentation.isEnabled = this.store.mainToolbar.analyzing
        }
    }

}