package net.ntworld.intellijCodeCleaner

import com.intellij.openapi.project.Project as IdeaProject
import net.ntworld.redux.Dispatcher

class DefaultDispatcher private constructor() : Dispatcher<AppStore> {
    override val store: AppStore = DefaultStore()

    companion object {
        private val dispatchers = mutableMapOf<IdeaProject, DefaultDispatcher>()

        fun getInstance(ideaProject: IdeaProject): DefaultDispatcher {
            if (!dispatchers.containsKey(ideaProject)) {
                dispatchers[ideaProject] = DefaultDispatcher()
            }
            return dispatchers[ideaProject]!!
        }

        fun findInstance(projectId: String): DefaultDispatcher {
            for (item in dispatchers) {
                if (projectId == item.value.store.project.id) {
                    return item.value
                }
            }
            throw Exception("Project $projectId is not found!")
        }
    }
}