package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.ToggleAnnotationAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class AnnotationToggleButton(private val plugin: Plugin) : ToggleAction(null, null, Icons.ShowAnnotation) {
    override fun isSelected(e: AnActionEvent): Boolean {
        return plugin.store.mainToolbar.hasData && plugin.store.mainToolbar.openingAnnotations
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        plugin.dispatch(ToggleAnnotationAction())
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = plugin.store.mainToolbar.hasData
    }
}