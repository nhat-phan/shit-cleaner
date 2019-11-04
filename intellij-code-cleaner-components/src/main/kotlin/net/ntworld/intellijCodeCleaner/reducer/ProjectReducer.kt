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
    override val log: Boolean = false

    private fun logIssues(prefix: String, data: Map<String, Issue>) = data.forEach { (key, item) ->
        log("$prefix   $key => {")
        log("$prefix     path: ${item.path}")
        log("$prefix     fileRate: ${item.fileRate}")
        log("$prefix     description: ${item.description}")
        log("$prefix     lines: ${item.lines}")
        log("$prefix     locations: ${item.locations}")
        log("$prefix   }")
    }

    override fun logState(state: ProjectState, output: Boolean) {
        val prefix = if (output) "OUT: " else "IN : "

        log("$prefix id = ${state.id}")
        log("$prefix basePath = ${state.basePath}")
        log("$prefix contentRoots = [")
        for (i in 0..state.contentRoots.lastIndex) {
            log("$prefix   $i = ${state.contentRoots[i]}")
        }
        log("$prefix ]")
        log("$prefix initialized = ${state.initialized}")
        log("$prefix analyzing = ${state.analyzing}")
        log("$prefix counting = ${state.counting}")
        log("$prefix hasResult = ${state.hasResult}")
        log("$prefix codeSmells = (")
        logIssues(prefix, state.codeSmells)
        log("$prefix )")
        log("$prefix duplications = (")
        logIssues(prefix, state.duplications)
        log("$prefix )")
        log("$prefix codeStatisticData = ${state.codeStatisticData}")
        log("$prefix time = ${state.time}")
    }

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
        return data
            .filter {
                for (contentRoot in contentRoots) {
                    if (it.path.startsWith(contentRoot.path)) {
                        return@filter true
                    }
                }
                false
            }
            .fold(mutableMapOf()) { acc, item ->
                acc[item.id] = item
                acc
            }
    }
}