package net.ntworld.codeCleaner.event

import net.ntworld.foundation.Event

interface AnalyzeProcessStoppedEvent : Event {
    val projectId: String

    companion object
}