package net.ntworld.intellijCodeCleaner

import net.ntworld.intellijCodeCleaner.state.MainToolbarState
import net.ntworld.intellijCodeCleaner.state.ProjectState
import net.ntworld.redux.Store

interface AppStore: Store {
    val project: ProjectState

    val mainToolbar: MainToolbarState
}