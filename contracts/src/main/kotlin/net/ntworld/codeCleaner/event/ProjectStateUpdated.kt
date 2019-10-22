package net.ntworld.codeCleaner.event

import net.ntworld.codeCleaner.structure.ProjectState
import net.ntworld.foundation.Event

interface ProjectStateUpdated : Event {
    val projectId: String

    val state: ProjectState

    companion object
}