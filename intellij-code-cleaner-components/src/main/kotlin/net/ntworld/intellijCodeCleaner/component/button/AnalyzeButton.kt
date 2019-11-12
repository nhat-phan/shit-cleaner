package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.TOOLTIP_ANALYZE_BUTTON
import net.ntworld.intellijCodeCleaner.action.RequestAnalyzeAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class AnalyzeButton(private val componentFactory: ComponentFactory) :
    AnAction(null, TOOLTIP_ANALYZE_BUTTON, Icons.Analyze) {

    override fun actionPerformed(e: AnActionEvent) {
        componentFactory.useDispatcherOf(e.project) {
            this dispatch RequestAnalyzeAction.make(componentFactory, this.store.project.id, e.project!!)
        }
    }

    override fun update(e: AnActionEvent) {
        componentFactory.useDispatcherOf(e.project) {
            e.presentation.isEnabled = !this.store.mainToolbar.analyzing
        }
    }

}
