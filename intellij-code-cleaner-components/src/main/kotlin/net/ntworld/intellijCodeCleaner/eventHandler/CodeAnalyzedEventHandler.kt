package net.ntworld.intellijCodeCleaner.eventHandler

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction

@Handler
class CodeAnalyzedEventHandler(
    private val componentFactory: ComponentFactory
) : EventHandler<CodeAnalyzedEvent> {

    override fun handle(event: CodeAnalyzedEvent) {
        val plugin = componentFactory.makeDispatcher()

        plugin dispatch CodeAnalyzedAction.make(
            componentFactory, event.projectId, event.codeQualityId
        )
        if (plugin.store.mainToolbar.openingAnnotations) {
            ApplicationManager.getApplication().invokeLater {
                ProjectManager.getInstance().openProjects.forEach {
                    componentFactory.makeAnnotationManager(it).show()
                }
            }
        }
    }

}