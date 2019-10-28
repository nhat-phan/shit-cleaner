package net.ntworld.codeCleaner.event

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.foundation.Event

interface CodeAnalyzedEvent: Event {
    val codeQualityId: String

    val projectId: String

    companion object
}