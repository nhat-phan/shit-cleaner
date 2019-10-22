package net.ntworld.codeCleaner.event

import net.ntworld.foundation.Event

interface ProjectsSelected : Event {
    val projectIds: List<String>

    companion object
}