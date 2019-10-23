package net.ntworld.intellijCodeCleaner.action

import net.ntworld.intellijCodeCleaner.PROJECT_INITIALIZED

class ProjectInitializedAction(projectId: String) : AbstractProjectIdAction(projectId) {
    override val type: String = PROJECT_INITIALIZED
}