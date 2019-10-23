package net.ntworld.codeCleaner.event

import net.ntworld.foundation.Event

interface CodeAnalyzedEvent: Event {
    val projectId: String

    val raw: String

    companion object
}