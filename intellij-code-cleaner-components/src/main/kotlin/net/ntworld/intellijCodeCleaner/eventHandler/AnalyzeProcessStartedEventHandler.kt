package net.ntworld.intellijCodeCleaner.eventHandler

import net.ntworld.codeCleaner.event.AnalyzeProcessStartedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.RequestAnalyzeSuccessAction

@Handler
class AnalyzeProcessStartedEventHandler(
    private val plugin: Plugin
) : EventHandler<AnalyzeProcessStartedEvent> {

    override fun handle(event: AnalyzeProcessStartedEvent) {
        plugin dispatch RequestAnalyzeSuccessAction.make(event.projectId)
    }

}