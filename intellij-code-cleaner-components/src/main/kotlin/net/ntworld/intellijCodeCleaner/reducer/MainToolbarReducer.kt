package net.ntworld.intellijCodeCleaner.reducer

import net.ntworld.intellijCodeCleaner.REQUEST_ANALYZE_SUCCESS
import net.ntworld.intellijCodeCleaner.REQUEST_STOP_ANALYZE_SUCCESS
import net.ntworld.intellijCodeCleaner.TOGGLE_ANNOTATION
import net.ntworld.intellijCodeCleaner.state.MainToolbarState
import net.ntworld.redux.Action
import net.ntworld.redux.Reducer

class MainToolbarReducer : Reducer<MainToolbarState>(MainToolbarState.Default) {

    override fun reduce(state: MainToolbarState, action: Action<*>): MainToolbarState {
        return when (action.type) {
            REQUEST_ANALYZE_SUCCESS -> state.copy(analyzing = true)
            REQUEST_STOP_ANALYZE_SUCCESS -> state.copy(analyzing = false)
            TOGGLE_ANNOTATION -> state.copy(openingAnnotations = !state.openingAnnotations)

            else -> state
        }
    }

}