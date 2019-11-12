package net.ntworld.intellijCodeCleaner.component.button

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import net.ntworld.intellijCodeCleaner.AppStore
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.TOOLTIP_ANNOTATION_BUTTON
import net.ntworld.intellijCodeCleaner.action.ToggleAnnotationAction
import net.ntworld.intellijCodeCleaner.component.util.Icons
import net.ntworld.redux.Dispatcher

open class AnnotationToggleButton(private val componentFactory: ComponentFactory) :
    ToggleAction(null, TOOLTIP_ANNOTATION_BUTTON, Icons.ShowAnnotation) {

    override fun isSelected(e: AnActionEvent): Boolean {
        val project = e.project
        if (null === project) {
            return false
        }
        val dispatcher = componentFactory.makeDispatcher(project)
        return hasData(dispatcher) && dispatcher.store.mainToolbar.openingAnnotations
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        val project = e.project
        if (null === project) {
            return
        }
        val dispatcher = componentFactory.makeDispatcher(project)
        dispatcher dispatch ToggleAnnotationAction()

        val manager = componentFactory.makeAnnotationManager(project)
        if (state) {
            manager.show()
        } else {
            manager.hide()
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        val project = e.project
        if (null !== project) {
            val dispatcher = componentFactory.makeDispatcher(project)
            e.presentation.isEnabled = hasData(dispatcher)
        }
    }

    protected open fun hasData(dispatcher: Dispatcher<AppStore>): Boolean {
        return dispatcher.store.project.hasResult
    }
}