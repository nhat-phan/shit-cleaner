package net.ntworld.intellijCodeCleaner.eventHandler

import net.ntworld.codeCleaner.event.AnalyzeProcessStartedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.action.RequestAnalyzeSuccessAction

@Handler
class AnalyzeProcessStartedEventHandler(
    private val componentFactory: ComponentFactory
) : EventHandler<AnalyzeProcessStartedEvent> {

    override fun handle(event: AnalyzeProcessStartedEvent) {
        componentFactory.findDispatcher(event.projectId) dispatch RequestAnalyzeSuccessAction(event.projectId)
    }

}