package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.ToggleAnnotationAction
import net.ntworld.intellijCodeCleaner.component.util.Icons

open class AnnotationToggleButton(private val componentFactory: ComponentFactory) :
    ToggleAction(null, null, Icons.ShowAnnotation) {
    private val plugin = componentFactory.makeDispatcher()

    override fun isSelected(e: AnActionEvent): Boolean {
        return hasData() && plugin.store.mainToolbar.openingAnnotations
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        plugin dispatch ToggleAnnotationAction()
        val project = e.project
        if (null === project) {
            return
        }

        val manager = componentFactory.makeAnnotationManager(project)
        if (state) {
            manager.show()
        } else {
            manager.hide()
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = hasData()
    }

    protected open fun hasData(): Boolean {
        return plugin.store.project.hasResult
    }
}