package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction
import net.ntworld.intellijCodeCleaner.action.CodeStatisticFinishedAction
import net.ntworld.intellijCodeCleaner.action.ProjectInitializedAction
import net.ntworld.intellijCodeCleaner.data.ContentRootInfo
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.intellijCodeCleaner.util.IdeaProjectUtil
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer
import org.joda.time.DateTime

class ProjectReducer : Reducer<ProjectState>(ProjectState.Default) {
    override val log: Boolean = true

    override fun reduce(state: ProjectState, action: Action<*>): ProjectState {
        return when (action.type) {
            PROJECT_INITIALIZED -> reduceWhenProjectInitialized(state, action as ProjectInitializedAction)

            REQUEST_ANALYZE_SUCCESS -> {
                state.copy(
                    analyzing = true,
                    hasResult = false,
                    codeSmells = mapOf(),
                    duplications = mapOf(),
                    time = DateTime.now()
                )
            }

            REQUEST_STOP_ANALYZE_SUCCESS -> state.copy(analyzing = false)

            CODE_ANALYZED -> reduceWhenCodeAnalyzed(state, action as CodeAnalyzedAction)

            CODE_STATISTIC_STARTED -> state.copy(counting = true)
            CODE_STATISTIC_FINISHED -> reduceWhenCodeStatisticFinished(state, action as CodeStatisticFinishedAction)

            else -> state
        }
    }

    private fun reduceWhenProjectInitialized(state: ProjectState, action: ProjectInitializedAction): ProjectState {
        return state.copy(
            id = action.payload.projectId,
            basePath = action.payload.basePath,
            contentRoots = action.payload.contentRoots
        )
    }

    private fun reduceWhenCodeStatisticFinished(
        state: ProjectState,
        action: CodeStatisticFinishedAction
    ): ProjectState {
        if (state.hasResult) {
            return state.copy(
                counting = false,
                contentRoots = action.payload.contentRoots,
                codeStatisticData = action.payload.data,
                codeSmells = buildIssueMap(action.payload.contentRoots, state.codeSmells.values),
                duplications = buildIssueMap(action.payload.contentRoots, state.duplications.values)
            )
        }
        return state.copy(
            counting = false,
            contentRoots = action.payload.contentRoots,
            codeStatisticData = action.payload.data
        )
    }

    private fun reduceWhenCodeAnalyzed(state: ProjectState, action: CodeAnalyzedAction): ProjectState {
        return state.copy(
            analyzing = false,
            hasResult = true,
            codeSmells = buildIssueMap(state.contentRoots, action.payload.codeSmells),
            duplications = buildIssueMap(state.contentRoots, action.payload.duplications),
            time = action.payload.createdAt
        )
    }

    private fun buildIssueMap(contentRoots: List<ContentRootInfo>, data: Collection<Issue>): Map<String, Issue> {
        val result = mutableMapOf<String, Issue>()
        for (item in data) {
            result[item.id] = item
        }
        return result
    }
}