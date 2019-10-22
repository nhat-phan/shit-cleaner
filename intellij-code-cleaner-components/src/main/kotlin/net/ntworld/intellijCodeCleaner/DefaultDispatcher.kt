package net.ntworld.intellijCodeCleaner

import net.ntworld.redux.Dispatcher

object DefaultDispatcher : Dispatcher<AppStore> {
    override val store = DefaultStore
}