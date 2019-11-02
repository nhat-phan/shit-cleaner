package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.codeCleaner.structure.MaintainabilityRate
import net.ntworld.intellijCodeCleaner.*
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
            CODE_ANALYZED -> state.copy(analyzing = false)

            else -> state
        }
    }

}