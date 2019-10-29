package net.ntworld.codeCleaner.event

import net.ntworld.foundation.Event

interface CodeAnalyzedEvent: Event {
    val codeQualityId: String

    val projectId: String

    companion object
}