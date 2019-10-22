package net.ntworld.intellijCodeCleaner

import net.ntworld.codeCleaner.make
import net.ntworld.codeCleaner.query.FindProjectStateByIdQuery
import net.ntworld.codeCleaner.structure.Project

// TODO
object IntellijCodeCleanerPlugin {
    private val projects = mutableMapOf<String, Project>()

    val selectedProjectIds: List<String>
        get() {
            return projects.keys.toList()
        }

    fun registerProject(project: Project) {
        projects[project.id] = project
    }

    fun hasAnyAnalyzeProcessRunning(): Boolean {
        val infrastructure = IntellijCodeCleaner()
        for (id in selectedProjectIds) {
            val state = infrastructure.queryBus().process(FindProjectStateByIdQuery.make(id))
            if (state.isRunning) {
                return true
            }
        }
        return false
    }
}