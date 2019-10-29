package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction
import net.ntworld.intellijCodeCleaner.action.CodeStatisticFinishedAction
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer
import org.joda.time.DateTime

class ProjectReducer : Reducer<ProjectState>(ProjectState.Default) {
    override val log: Boolean = true

    override fun reduce(state: ProjectState, action: Action<*>): ProjectState {
        return when (action.type) {
            PROJECT_INITIALIZED -> {
                state.copy(id = action.payload!!["projectId"] as String, initialized = true)
            }

            REQUEST_ANALYZE_SUCCESS -> {
                state.copy(
                    analyzing = true,
                    hasResult = false,
                    codeSmells = listOf(),
                    duplications = listOf(),
                    time = DateTime.now()
                )
            }

            REQUEST_STOP_ANALYZE_SUCCESS -> state.copy(analyzing = false)

            CODE_ANALYZED -> {
                val payload = (action as CodeAnalyzedAction).payload
                state.copy(
                    analyzing = false,
                    hasResult = true,
                    codeSmells = payload.codeSmells,
                    duplications = payload.duplications,
                    time = payload.createdAt
                )
            }

            CODE_STATISTIC_STARTED -> state.copy(counting = true)
            CODE_STATISTIC_FINISHED -> {
                state.copy(
                    counting = false,
                    codeStatisticData = (action as CodeStatisticFinishedAction).payload.data
                )
            }

            else -> state
        }
    }

}