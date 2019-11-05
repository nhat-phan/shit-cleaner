package net.ntworld.intellijCodeCleaner.eventHandler

import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.ComponentFactory
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction

@Handler
class CodeAnalyzedEventHandler(
    private val componentFactory: ComponentFactory
) : EventHandler<CodeAnalyzedEvent> {

    override fun handle(event: CodeAnalyzedEvent) {
        componentFactory.makeDispatcher() dispatch CodeAnalyzedAction.make(
            componentFactory, event.projectId, event.codeQualityId
        )
    }

}