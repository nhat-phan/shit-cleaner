package net.ntworld.tmp

import net.ntworld.codeCleaner.event.AnalyzeProcessStartedEvent
import net.ntworld.codeCleaner.event.AnalyzeProcessStoppedEvent
import net.ntworld.codeCleaner.event.CodeAnalyzedEvent
import net.ntworld.foundation.EventHandler
import net.ntworld.foundation.Handler

@Handler
class AnalyzeProcessStartedEventHandler: EventHandler<AnalyzeProcessStartedEvent> {
    override fun handle(event: AnalyzeProcessStartedEvent) {
        println("Start $event")
    }
}

@Handler
class AnalyzeProcessStoppedEventHandler: EventHandler<AnalyzeProcessStoppedEvent> {
    override fun handle(event: AnalyzeProcessStoppedEvent) {
        println("Stop $event")
    }
}

@Handler
class CodeAnalyzedEventHandler: EventHandler<CodeAnalyzedEvent> {
    override fun handle(event: CodeAnalyzedEvent) {
        println("Stop $event")
    }
}