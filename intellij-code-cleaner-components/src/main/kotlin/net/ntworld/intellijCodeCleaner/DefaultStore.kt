package net.ntworld.intellijCodeCleaner

import net.ntworld.intellijCodeCleaner.reducer.MainToolbarReducer
import net.ntworld.intellijCodeCleaner.reducer.ProjectReducer
import net.ntworld.redux.StoreBase

class DefaultStore : AppStore, StoreBase() {
    override val project by reducer(ProjectReducer())

    override val mainToolbar by reducer(MainToolbarReducer())
}