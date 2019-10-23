package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.intellijCodeCleaner.CODE_ANALYZED
import net.ntworld.intellijCodeCleaner.PROJECT_INITIALIZED
import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE_SUCCESS
import net.ntworld.intellijCodeCleaner.REQUEST_STOP_ANALYZE_SUCCESS
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer
import org.joda.time.DateTime

class ProjectReducer : Reducer<ProjectState>(ProjectState.Default) {

    override fun reduce(state: ProjectState, action: Action<*>): ProjectState {
        return when (action.type) {
            PROJECT_INITIALIZED -> {
                state.copy(id = action.payload!!["projectId"] as String, initialized = true)
            }

            REQUEST_ANALYZE_SUCCESS -> {
                state.copy(analyzing = true, lastRunAt = DateTime.now())
            }

            REQUEST_STOP_ANALYZE_SUCCESS -> {
                state.copy(analyzing = false)
            }

            CODE_ANALYZED -> {
                state.copy(analyzing = false, parsing = true)
            }

            else -> state
        }
    }

}