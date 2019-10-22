package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.intellijCodeCleaner.PROJECT_INITIALIZED
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer

class ProjectReducer : Reducer<ProjectState>(ProjectState.Default) {

    override fun reduce(state: ProjectState, action: Action<*>): ProjectState {
        return when (action.type) {
            PROJECT_INITIALIZED -> {
                state.copy(id = action.payload!!["projectId"] as String, initialized = true)
            }
            else -> state
        }
    }

}