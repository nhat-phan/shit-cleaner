package net.ntworld.redux

interface Store {
    fun reduce(action: Action<*>): Boolean

    fun onChange(key: String, block: () -> Unit)

    fun onChange(block: () -> Unit)
}