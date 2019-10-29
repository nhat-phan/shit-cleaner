package net.ntworld.intellijCodeCleaner.eventHandler

import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler
import net.ntworld.intellijCodeCleaner.Plugin
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction

@Handler
class CodeAnalyzedEventHandler(private val plugin: Plugin) : EventHandler<CodeAnalyzedEvent> {

    override fun handle(event: CodeAnalyzedEvent) {
        plugin dispatch CodeAnalyzedAction.make(event.projectId, event.codeQualityId)
    }

}