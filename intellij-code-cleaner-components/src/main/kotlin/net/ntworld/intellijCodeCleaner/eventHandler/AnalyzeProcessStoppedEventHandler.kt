package net.ntworld.intellijCodeCleaner.eventHandler

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
        componentFactory.makeDispatcher() dispatch RequestStopAnalyzeSuccessAction(event.projectId)
    }

}