package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.codeCleaner.structure.Issue
import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.*
import net.ntworld.intellijCodeCleaner.action.CodeAnalyzedAction
import net.ntworld.intellijCodeCleaner.action.ToggleMaintainabilityFilterAction
import net.ntworld.intellijCodeCleaner.state.MainToolbarState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer

class MainToolbarReducer : Reducer<MainToolbarState>(MainToolbarState.Default) {

    override fun reduce(state: MainToolbarState, action: Action<*>): MainToolbarState {
        return when (action.type) {
            REQUEST_ANALYZE_SUCCESS -> state.copy(analyzing = true)
            REQUEST_STOP_ANALYZE_SUCCESS -> state.copy(analyzing = false)
            TOGGLE_ANNOTATION -> state.copy(openingAnnotations = !state.openingAnnotations)
            TOGGLE_MAINTAINABILITY_FILTER -> {
                when ((action as ToggleMaintainabilityFilterAction).payload.rate) {
                    MaintainabilityRate.Good -> state.copy(filteringByGoodIssues = !state.filteringByGoodIssues)
                    MaintainabilityRate.Moderate -> state.copy(filteringByModerateIssues = !state.filteringByModerateIssues)
                    MaintainabilityRate.Bad -> state.copy(filteringByBadIssues = !state.filteringByBadIssues)
                }
            }
            CODE_ANALYZED -> reduceWhenCodeAnalyzed(state, action as CodeAnalyzedAction)

            else -> state
        }
    }

    private fun reduceWhenCodeAnalyzed(state: MainToolbarState, action: CodeAnalyzedAction): MainToolbarState {
        var hasBadIssues = false
        var hasModerateIssues = false
        var hasGoodIssues = false
        val loop: (List<Issue>) -> Unit = {
            for (issue in it) {
                when (issue.fileRate) {
                    MaintainabilityRate.Good -> hasGoodIssues = true
                    MaintainabilityRate.Moderate -> hasModerateIssues = true
                    MaintainabilityRate.Bad -> hasBadIssues = true
                }
                if (hasGoodIssues && hasModerateIssues && hasBadIssues) {
                    break
                }
            }
        }

        loop(action.payload.codeSmells)
        loop(action.payload.duplications)
        return state.copy(
            analyzing = false,
            hasData = true,
            hasBadIssues = hasBadIssues,
            hasModerateIssues = hasModerateIssues,
            hasGoodIssues = hasGoodIssues
        )
    }

}