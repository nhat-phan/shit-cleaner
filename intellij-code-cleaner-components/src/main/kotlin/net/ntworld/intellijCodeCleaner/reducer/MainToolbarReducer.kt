package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.intellijCodeCleaner.ANALYZE_PROJECT
import net.ntworld.intellijCodeCleaner.STOP_ANALYZE_PROJECT
import net.ntworld.intellijCodeCleaner.TOGGLE_ANNOTATION
import net.ntworld.intellijCodeCleaner.state.MainToolbarState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer

class MainToolbarReducer : Reducer<MainToolbarState>(MainToolbarState.Default) {

    override fun reduce(state: MainToolbarState, action: Action<*>): MainToolbarState {
        return when (action.type) {
            ANALYZE_PROJECT -> state.copy(analyzing = true)
            STOP_ANALYZE_PROJECT -> state.copy(analyzing = false)
            TOGGLE_ANNOTATION -> state.copy(openingAnnotations = !state.openingAnnotations)

            else -> state
        }
    }

}