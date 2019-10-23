package net.ntworld.intellijCodeCleaner.eventHandler

import net.ntworld.codeCleaner.event.AnalyzeProcessStoppedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.RequestStopAnalyzeSuccessAction

@Handler
class AnalyzeProcessStoppedEventHandler(
    private val plugin: Plugin
) : EventHandler<AnalyzeProcessStoppedEvent> {

    override fun handle(event: AnalyzeProcessStoppedEvent) {
        plugin dispatch RequestStopAnalyzeSuccessAction.make(event.projectId)
    }

}