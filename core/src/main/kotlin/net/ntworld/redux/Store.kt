package net.ntworld.redux

interface Store {
    fun reduce(action: Action<*>): Boolean
}