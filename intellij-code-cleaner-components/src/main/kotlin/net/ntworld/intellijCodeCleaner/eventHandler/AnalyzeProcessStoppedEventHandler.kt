package net.ntworld.intellijCodeCleaner.eventHandler

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import net.ntworld.codeCleaner.event.AnalyzeProcessStoppedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeSuccessAction

@Handler
class AnalyzeProcessStoppedEventHandler(
    private val componentFactory: ComponentFactory
) : EventHandler<AnalyzeProcessStoppedEvent> {

    override fun handle(event: AnalyzeProcessStoppedEvent) {
        ApplicationManager.getApplication().invokeLater {
            if (event.error.isNotEmpty()) {
                Messages.showErrorDialog(event.error, "Error")
            }
        }
        componentFactory.findDispatcher(event.projectId) dispatch RequestStopAnalyzeSuccessAction(event.projectId)
    }

}